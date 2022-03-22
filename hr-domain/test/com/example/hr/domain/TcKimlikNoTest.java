package com.example.hr.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class TcKimlikNoTest {

	@DisplayName("creating tc kimlik no successfully") 
	@ParameterizedTest
	@CsvFileSource(resources = "tckimlikno-valid.csv")
	void createTcKimlikNoSuccessfully(String value) {
		var kimlikNo = TcKimlikNo.valueOf(value);
		assertEquals(value,kimlikNo.getValue());
	}
	
	@DisplayName("creating tc kimlik no fails") 
	@ParameterizedTest
	@CsvFileSource(resources = "tckimlikno-invalid.csv")
	void createTcKimlikNoFails(String value) {
		assertThrows(IllegalArgumentException.class,
				() -> TcKimlikNo.valueOf(value));
	}

}
