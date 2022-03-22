package com.example.hr.application.infrastructure;

import com.example.hr.application.business.events.EmployeeEvent;

public interface EventPublisher {

	void publish(EmployeeEvent businessEvent);

}
