package com.example.crm.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.crm.event.CustomerEvent;
import com.example.crm.repository.CustomerDocumentRepository;

@Service
public class CustomerEventListener {
	private final CustomerDocumentRepository customerDocumentRepository;
	
	public CustomerEventListener(CustomerDocumentRepository customerDocumentRepository) {
		this.customerDocumentRepository = customerDocumentRepository;
	}

	@RabbitListener(queues = "custeventqueue")
	public void listenCustomerEvents(CustomerEvent event) {
		// TODO: Event -> CustomerDocument
		// CustomerAcquiredEvent -> new CustomerDocument() -> save
		// CustomerReleasedEvent -> delete
		// Projections
	}
}
