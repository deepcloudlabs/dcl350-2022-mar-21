package com.example;

public class CircleApp {

	public static void main(String[] args) {
		var circle1 = new Circle(0,0,100);
		System.out.println(circle1);
		System.out.println(circle1.x());
		System.out.println(circle1.y());
		System.out.println(circle1.radius());
		System.out.println(circle1.area());
		System.out.println(circle1.circumference());
	}

}
