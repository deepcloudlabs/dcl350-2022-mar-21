package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.entity.EmployeeEntity;

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
	
	private static final Converter<Employee,EmployeeEntity> EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER = context -> {
		var employee = context.getSource();
		var employeeEntity = new EmployeeEntity();
		employeeEntity.setIdentity(employee.getIdentity().getValue());
		employeeEntity.setFirstName(employee.getFullName().getFirstName());
		employeeEntity.setLastName(employee.getFullName().getLastName());
		employeeEntity.setIban(employee.getIban().getValue());
		employeeEntity.setSalary(employee.getSalary().getValue());
		employeeEntity.setDepartment(employee.getDepartment());
		employeeEntity.setJobStyle(employee.getJobStyle());
		employeeEntity.setPhoto(employee.getPhoto().getValue());
		return employeeEntity;
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
	
	private static final Converter<EmployeeEntity,Employee> EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER = context -> {
		var entity = context.getSource();
		return new Employee.Builder(entity.getIdentity())
				.fullName(entity.getFirstName(), entity.getLastName())
				.iban(entity.getIban())
				.salary(entity.getSalary())
				.photo(entity.getPhoto())
				.department(entity.getDepartment())
				.jobStyle(entity.getJobStyle())
				.build();
	};
	
	@Bean
	public ModelMapper modelMapper() {
		var mapper = new ModelMapper();
		mapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		mapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, HireEmployeeRequest.class, Employee.class);
		mapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER, Employee.class, EmployeeEntity.class);
		mapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER, EmployeeEntity.class, Employee.class);
		return mapper;
	}
}
