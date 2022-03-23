package com.example.hr.adapter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.hr.application.business.events.EmployeeEvent;
import com.example.hr.application.infrastructure.EventPublisher;

@Service
public class EventPublisherApplicationEventPublisherAdapter implements EventPublisher {
	private final ApplicationEventPublisher publisher;

	public EventPublisherApplicationEventPublisherAdapter(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void publish(EmployeeEvent event) {
		publisher.publishEvent(event);
	}

}
