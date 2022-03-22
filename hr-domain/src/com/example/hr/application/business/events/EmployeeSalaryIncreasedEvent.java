package com.example.hr.application.business.events;

import com.example.hr.domain.Salary;
import com.example.hr.domain.TcKimlikNo;

public class EmployeeSalaryIncreasedEvent extends EmployeeEvent {

	private final Salary salary;
	private final double rate;

	public EmployeeSalaryIncreasedEvent(TcKimlikNo identity,Salary newSalary,double rate) {
		super(identity);
		this.salary = newSalary;
		this.rate = rate;
	}

	public Salary getSalary() {
		return salary;
	}

	public double getRate() {
		return rate;
	}

}
