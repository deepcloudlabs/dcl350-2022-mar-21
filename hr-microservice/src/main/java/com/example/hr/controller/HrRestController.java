package com.example.hr.controller;

import javax.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.dto.response.UpdateEmployeeSalaryResponse;
import com.example.hr.service.HrService;
import com.example.validation.TcKimlikNo;

// REST API Design Approaches:
// 1. Resource->Model/Entity Oriented
//    URI/GET,PUT,.../Representation
//    Representation? HTTP -> Text-based (XML/JSON/SVG/...) ->
//    Query -> GET
//    Create -> POST
//    Update -> PUT/PATCH
//    Remove -> DELETE
//   3. GraphQL -> Proxy -> 1+ MS
//    Client -- Schema ->
// 2. RPC-style
//    gRPC -> Protocol Buffer 
//    Spring for gRPC
//    URL -> method name -> Command
@RestController
@RequestScope
@RequestMapping("employees")
@Validated
@CrossOrigin
// Adapter: HTTP Protocol <--> Java Class
public class HrRestController {

	private final HrService hrService;
	
	public HrRestController(HrService hrService) {
		this.hrService = hrService;
	}
    // 1. HTTP Method: GET, POST, PUT, PATCH, DELETE, ...
	// 2. URL
	// GET http://localhost:8100/hr/api/v1/employees/11111111110
	@GetMapping("{identity}")
	public EmployeeResponse findById(@PathVariable @TcKimlikNo String identity) {		
		return hrService.findEmployee(identity);
	}

	/* GET http://localhost:8100/hr/api/v1/employees
	@GetMapping
	public List<EmployeeResponse> findAllEmployeesByPage(
			@RequestParam @Min(0) int pageNo,
			@RequestParam @Max(25) int pageSize) {		
		return hrService.findAll(pageNo,pageSize);
	}
	*/
	// POST /
	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody @Validated HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}
	
	// DELETE /11111111110
	@DeleteMapping("{identity}")
	public FireEmployeeResponse fireEmployee(@PathVariable @TcKimlikNo String identity) {
		return hrService.fireEmployee(identity);
	}
	
	// PUT /
	// /employees?department=SALES&rate=12.0
	@PutMapping(params = {"department", "rate"})
	public UpdateEmployeeSalaryResponse updateEmployeeSalary(
			@RequestParam String department,
			@RequestParam @Positive double rate) {
		return hrService.increaseDepartmentSalaries(department,rate);
	}
	
}
