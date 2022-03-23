package com.example.hr.service;

import javax.validation.constraints.Positive;

import org.springframework.stereotype.Service;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.dto.response.UpdateEmployeeSalaryResponse;

@Service
public class HrService {

	public EmployeeResponse findEmployee(String identity) {
		return null;
	}

	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		return null;
	}

	public FireEmployeeResponse fireEmployee(String identity) {
		return null;
	}

	public UpdateEmployeeSalaryResponse increaseDepartmentSalaries(String department, @Positive double rate) {
		return null;
	}

}
