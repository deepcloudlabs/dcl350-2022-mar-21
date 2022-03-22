package com.example.hr.repository;

import java.util.List;
import java.util.Optional;

import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

public interface EmployeeRepository {

	boolean exists(TcKimlikNo identity);

	Employee save(Employee employee);

	Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity);
	
	Optional<Employee> removeEmployee(Employee employee);

	List<Employee> findEmployeesByDepartment(Department department);

	Employee update(Employee employee);

}
