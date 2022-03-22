package com.example;

public class StudyEnum {

	public static void main(String[] args) {
		// var jobStyle = JobStyle.valueOf("FUL_TIME");
		for (var js : JobStyle.values()) {
			System.err.println(js.name()+": "+js.ordinal());
		}
	}

}
