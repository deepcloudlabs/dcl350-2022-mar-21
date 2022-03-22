package com.example.hr.domain;

import java.util.Objects;

@ValueObject
public final class FullName {
	private final String firstName;
	private final String lastName;

	private FullName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public static FullName of(String firstName, String lastName) {
		Objects.requireNonNull(firstName, "Firstname must have a value!");
		Objects.requireNonNull(lastName, "Firstname must have a value!");
		return new FullName(firstName, lastName);
	}
}
