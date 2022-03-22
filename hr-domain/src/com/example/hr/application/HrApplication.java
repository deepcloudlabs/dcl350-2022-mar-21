package com.example.hr.application;

import java.util.List;
import java.util.Optional;

import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

// ISP: Interface Segregation Principle
public interface HrApplication {
	Employee hireEmployee(Employee employee);
	Optional<Employee> fireEmployee(TcKimlikNo identity);
	Optional<Employee> getEmployeeInformation(TcKimlikNo identity);
	List<Employee> increaseSalaryOfDepartmentEmployees(Department department,double rate);
}
