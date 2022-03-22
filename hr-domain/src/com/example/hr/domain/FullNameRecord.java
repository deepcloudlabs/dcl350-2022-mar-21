package com.example.hr.domain;

import java.util.Objects;

// Value Object -> record
public record FullNameRecord(String firstName,String lastName) {	
	public static FullNameRecord of(String firstName, String lastName) {
		Objects.requireNonNull(firstName, "Firstname must have a value!");
		Objects.requireNonNull(lastName, "Firstname must have a value!");
		return new FullNameRecord(firstName, lastName);
	}
}

