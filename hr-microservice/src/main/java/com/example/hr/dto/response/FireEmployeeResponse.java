package com.example.hr.dto.response;

public class FireEmployeeResponse {
	private String status;

	public FireEmployeeResponse() {
	}

	public FireEmployeeResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
