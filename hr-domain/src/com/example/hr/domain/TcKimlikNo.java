package com.example.hr.domain;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

// Value Object 
//  i) Has no identity, must belongs to Entity
// ii) immutable -> Java
@ValueObject
public final class TcKimlikNo {
	private static final Map<String, TcKimlikNo> pool = new ConcurrentHashMap<>();
	private final String value;

	private TcKimlikNo(String value) {
		this.value = value;
	}

	// factory method: static
	// validation, pooling, ... : cross-cutting concern!
	public static TcKimlikNo valueOf(String value) {
		// validation
		if (!isValid(value))
			throw new IllegalArgumentException("This is not a valid identity");
		// object pooling -> immutable
		var tcKimlikNo = pool.get(value);
		if (Objects.isNull(tcKimlikNo)) {
			tcKimlikNo = new TcKimlikNo(value);
			pool.put(value, tcKimlikNo);
		}
		return tcKimlikNo;
	}

	private static boolean isValid(String value) {
		if (value == null)
			return false;
		if (!value.matches("^\\d{11}$")) { // fail-fast
			return false;
		}
		int[] digits = new int[11];
		for (int i = 0; i < digits.length; ++i) {
			digits[i] = value.charAt(i) - '0';
		}
		int x = digits[0];
		int y = digits[1];
		for (int i = 1; i < 5; i++) {
			x += digits[2 * i];
		}
		for (int i = 2; i <= 4; i++) {
			y += digits[2 * i - 1];
		}
		int c1 = 7 * x - y;
		if (c1 % 10 != digits[9]) {
			return false;
		}
		int c2 = 0;
		for (int i = 0; i < 10; ++i) {
			c2 += digits[i];
		}
		if (c2 % 10 != digits[10]) {
			return false;
		}
		return true;
	}

	public String getValue() {
		return value;
	}

}
