package com.example.hr.domain;

import java.util.Base64;
import java.util.Objects;

@ValueObject
public final class Photo {
	private final byte[] value;

	private Photo(byte[] value) {
		this.value = value;
	}

	public byte[] getValue() {
		return value;
	}
	
	public String getBase64EncodedValue() {
		return Base64.getEncoder().encodeToString(value);
	}
	
	public static Photo of(byte[] value) {
		Objects.nonNull(value);
		return new Photo(value);
	}
	
	public static Photo of(String base64EncodedValue) {
		Objects.nonNull(base64EncodedValue);
		return new Photo(Base64.getDecoder().decode(base64EncodedValue));
	}
}
