package com.example.hr.application.business;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.events.EmployeeFiredEvent;
import com.example.hr.application.business.events.EmployeeHiredEvent;
import com.example.hr.application.business.events.EmployeeSalaryIncreasedEvent;
import com.example.hr.application.infrastructure.EventPublisher;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private final EmployeeRepository employeeRepository;
	private final EventPublisher eventPublisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		Objects.requireNonNull(employee,"You must provide an employee");
		var identity = employee.getIdentity();
		if (employeeRepository.exists(identity ))
			throw new IllegalArgumentException("Employee already exists!");
		Employee savedEmployee = employeeRepository.save(employee);
		var businessEvent = new EmployeeHiredEvent(employee.getIdentity());
		eventPublisher.publish(businessEvent);
		return savedEmployee;
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo identity) {
		var employee = employeeRepository.findEmployeeByIdentity(identity);
     	employee.ifPresent( employeeRepository::removeEmployee );	
     	var businessEvent = new EmployeeFiredEvent(identity);
     	eventPublisher.publish(businessEvent);
		return employee;
	}

	@Override
	public Optional<Employee> getEmployeeInformation(TcKimlikNo identity) {
		return employeeRepository.findEmployeeByIdentity(identity);
	}

	@Override
	public List<Employee> increaseSalaryOfDepartmentEmployees(Department department, double rate) {
		Function<Employee,Employee> increaseEmployeeSalary = 
				employee -> employee.increaseSalary(rate);
		Function<Employee,Employee> publishBusinessEvent = 
				employee -> {
					var event = new EmployeeSalaryIncreasedEvent(employee.getIdentity(),
							employee.getSalary(),rate);
					eventPublisher.publish(event);
					return employee;
				};
		return employeeRepository.findEmployeesByDepartment(department)
				.stream()
				.map(increaseEmployeeSalary.andThen(employeeRepository::update)
						                   .andThen(publishBusinessEvent))
				.toList();
	}

}
