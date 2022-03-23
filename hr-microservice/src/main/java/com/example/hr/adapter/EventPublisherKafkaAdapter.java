package com.example.hr.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.application.business.events.EmployeeEvent;
import com.example.hr.application.infrastructure.EventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventPublisherKafkaAdapter implements EventPublisher {
	private final String topicName;
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper mapper;
	
	public EventPublisherKafkaAdapter(
		@Value("${topicname}") String topicName, 
		KafkaTemplate<String, String> kafkaTemplate,
			ObjectMapper mapper) {
		this.topicName = topicName;
		this.kafkaTemplate = kafkaTemplate;
		this.mapper = mapper;
	}

	@Override
	public void publish(EmployeeEvent event) {
		try {
			var eventAsJson = mapper.writeValueAsString(event);
			kafkaTemplate.send(topicName,eventAsJson );			
		}catch (Exception e) {
			System.err.println("Error while converting to json: "+e.getMessage());
		}
	}

}
