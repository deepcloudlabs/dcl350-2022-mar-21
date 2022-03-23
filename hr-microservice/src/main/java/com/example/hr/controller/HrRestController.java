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

	@GetMapping("{identity}")
	public EmployeeResponse getEmployeeByIdentity(@PathVariable @TcKimlikNo String identity) {		
		return hrService.findEmployee(identity);
	}
	
	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody @Validated HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}
	
	@DeleteMapping("{identity}")
	public FireEmployeeResponse fireEmployee(@PathVariable @TcKimlikNo String identity) {
		return hrService.fireEmployee(identity);
	}
	
	// /employees?department=SALES&rate=12.0
	@PutMapping(params = {"department", "rate"})
	public UpdateEmployeeSalaryResponse updateEmployeeSalary(
			@RequestParam String department,
			@RequestParam @Positive double rate) {
		return hrService.increaseDepartmentSalaries(department,rate);
	}
	
}
