package com.example;

import java.util.List;

public class StudyObjectPooling {
	// -Djava.lang.Integer.IntegerCache.high=4096
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int x = 42; // 4-Byte
		// Auto-boxing
		// -128 <= i < 128 -> cache
		Integer i= 42; // 12-Byte (Object-Header) + 4-Byte = 16-Byte
		Integer j= Integer.valueOf(42); // Boxing 
		Integer u= 549;
		Integer v= 549;
		System.out.println("i==j? "+(i==j)); // true
		System.out.println("u==v? "+(u==v)); // false
		// List<int>: 4M --> List<Integer>: 16M
		List<Integer> numbers; // 
	}

}
