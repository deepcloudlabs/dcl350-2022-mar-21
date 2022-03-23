package com.example.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr.domain.Department;
import com.example.hr.entity.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String>{
	List<EmployeeEntity> findAllByDepartment(Department department);
	List<EmployeeEntity> findAllBySalaryBetween(double minSalary,double maxSalary);
}
