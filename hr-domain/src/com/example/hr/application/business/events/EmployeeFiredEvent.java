package com.example.hr.application.business.events;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeFiredEvent extends EmployeeEvent {

	public EmployeeFiredEvent(TcKimlikNo identity) {
		super(identity);
	}

}
