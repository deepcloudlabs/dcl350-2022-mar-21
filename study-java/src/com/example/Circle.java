package com.example;

public record Circle(double x, double y, double radius) {
	public double area() {
		return Math.PI * radius * radius;
	}
	public double circumference() {
		return 2. * Math.PI * radius;
	}
}
