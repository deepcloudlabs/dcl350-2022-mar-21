package com.example.hr.domain;

@ValueObject
public enum Department {
	HR(10), SALES(20), IT(30), FINANCE(200);
	private final int code;

	private Department(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
