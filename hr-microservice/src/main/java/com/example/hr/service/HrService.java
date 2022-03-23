package com.example.hr.service;

import java.util.Optional;

import javax.validation.constraints.Positive;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.dto.response.UpdateEmployeeSalaryResponse;

@Service
public class HrService {
	private HrApplication hrApplication;
	private ModelMapper modelMapper;
	
	public HrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	public EmployeeResponse findEmployee(String identity) {
		Optional<Employee> employee = hrApplication.getEmployeeInformation(TcKimlikNo.valueOf(identity));
		// Employee -- Model Mapper --> EmployeeResponse
		Employee emp = employee.orElseThrow( () -> new IllegalArgumentException("Cannot find employee"));
		return modelMapper.map(emp, EmployeeResponse.class);
				       
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var employee = modelMapper.map(request, Employee.class);
		hrApplication.hireEmployee(employee);
		return new HireEmployeeResponse("ok");
	}

	@Transactional
	public FireEmployeeResponse fireEmployee(String identity) {
		hrApplication.fireEmployee(TcKimlikNo.valueOf(identity))
		             .orElseThrow( () -> new IllegalArgumentException("Cannot find employee to fire"));
		return new FireEmployeeResponse("ok");
	}

	@Transactional
	public UpdateEmployeeSalaryResponse increaseDepartmentSalaries(String department, @Positive double rate) {
		var employees = hrApplication.increaseSalaryOfDepartmentEmployees(Department.valueOf(department), rate);
		return new UpdateEmployeeSalaryResponse("ok", employees.size());
	}

}
