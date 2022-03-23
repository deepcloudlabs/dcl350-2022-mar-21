package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;

@Configuration
public class ModelMapperConfig {
	private static final Converter<Employee,EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER = context -> {
		var employee = context.getSource();
		var employeeResponse = new EmployeeResponse();
		employeeResponse.setIdentity(employee.getIdentity().getValue());
		employeeResponse.setFirstName(employee.getFullName().getFirstName());
		employeeResponse.setLastName(employee.getFullName().getLastName());
		employeeResponse.setIban(employee.getIban().getValue());
		employeeResponse.setSalary(employee.getSalary().getValue());
		employeeResponse.setDepartment(employee.getDepartment());
		employeeResponse.setJobStyle(employee.getJobStyle());
		employeeResponse.setPhoto(employee.getPhoto().getBase64EncodedValue());
		return employeeResponse;
	};
	
	private static final Converter<HireEmployeeRequest,Employee> HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER = context -> {
		var request = context.getSource();
		return new Employee.Builder(request.getIdentity())
				           .fullName(request.getFirstName(), request.getLastName())
				           .iban(request.getIban())
				           .salary(request.getSalary())
				           .photo(request.getPhoto())
				           .department(request.getDepartment())
				           .jobStyle(request.getJobStyle())
				           .build();
	};
	
	@Bean
	public ModelMapper modelMapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		mapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, HireEmployeeRequest.class, Employee.class);
		return mapper;
	}
}
