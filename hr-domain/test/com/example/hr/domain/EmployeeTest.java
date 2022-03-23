package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class EmployeeTest {

	@DisplayName("Create a valid/consistent employee successfully")
	@ParameterizedTest
	@CsvFileSource(resources = "employees-valid.csv")
	void createEmployeeSuccessfully(String identity,String firstName,String lastName,
			String iban,double salary,String jobStyle, String department,String photo) {
		var jack = new Employee.Builder(identity)
				                .department(department)
				                .fullName(firstName, lastName)
				                .salary(salary)
				                .iban(iban)
				                .photo(photo)
				                .jobStyle(jobStyle)
				                .build();
		assertAll(
				() -> assertEquals(identity, jack.getIdentity().getValue()),
				() -> assertEquals(firstName, jack.getFullName().getFirstName()),
				() -> assertEquals(lastName, jack.getFullName().getLastName())
		);
	}

}
