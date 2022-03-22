package com.example.hr.application.business.events;

import com.example.hr.domain.TcKimlikNo;

public abstract class EmployeeEvent {
	protected final TcKimlikNo identity;

	public EmployeeEvent(TcKimlikNo identity) {
		this.identity = identity;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

}
