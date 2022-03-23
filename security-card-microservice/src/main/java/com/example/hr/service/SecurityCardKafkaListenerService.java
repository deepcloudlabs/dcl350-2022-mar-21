package com.example.hr.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SecurityCardKafkaListenerService {

	@KafkaListener(topics = "employee-events", groupId = "security-card")
	public void listenEmployeeEvents(String employeeEvent) {
		System.err.println(employeeEvent);
	}
}
