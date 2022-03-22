package com.example.hr.domain;

import java.util.Objects;

@ValueObject
public final class Salary {
	private final double value;
	private final FiatCurrency currency;
	
	private Salary(double value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}

	public double getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}
	
	public static Salary valueOf(double value) {
		return valueOf(value, FiatCurrency.TL);
	}
	
	public static Salary valueOf(double value, FiatCurrency currency) {
		Objects.requireNonNull(currency, "currency must be provided");
		if (value <= 0.)
			throw new IllegalArgumentException("salary must be positive");
		return new Salary(value, currency);
	}

	public Salary increment(double rate) {
		if (rate <= 0.0) 
			throw new IllegalArgumentException("Rate must be positive.");
		return Salary.valueOf((1.0 +rate)*this.getValue(), this.getCurrency());
	}
	
}
