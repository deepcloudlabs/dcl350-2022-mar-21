package com.example.hr.dto.response;

public class UpdateEmployeeSalaryResponse {
	private String status;
	private int numberOfEmployees;

	public UpdateEmployeeSalaryResponse() {
	}

	public UpdateEmployeeSalaryResponse(String status, int numberOfEmployees) {
		this.status = status;
		this.numberOfEmployees = numberOfEmployees;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(int numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

}
