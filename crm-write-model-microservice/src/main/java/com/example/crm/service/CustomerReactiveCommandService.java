package com.example.crm.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.command.AcquireCustomerCommand;
import com.example.crm.command.UpdateCustomerCommand;
import com.example.crm.dto.response.AcquireCustomerResponse;
import com.example.crm.dto.response.ReleaseCustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;
import com.example.crm.event.CustomerAcquiredEvent;
import com.example.crm.event.CustomerReleasedEvent;
import com.example.crm.repository.CustomerEventRepository;

import reactor.core.publisher.Mono;

@Service
public class CustomerReactiveCommandService {
	private final CustomerEventRepository customerEventRepository;
	private final RabbitTemplate rabbitTemplate;
	

	public CustomerReactiveCommandService(CustomerEventRepository customerEventRepository,
			RabbitTemplate rabbitTemplate) {
		this.customerEventRepository = customerEventRepository;
		this.rabbitTemplate = rabbitTemplate;
	}

	public Mono<AcquireCustomerResponse> createCustomer(AcquireCustomerCommand command) {
		var customerAcquiredEvent = new CustomerAcquiredEvent(command.getConversationId(),1,command.getIdentity());
		customerAcquiredEvent.setEventData(command);
		customerEventRepository.save(customerAcquiredEvent);
		rabbitTemplate.convertAndSend("custeventsexc", null, customerAcquiredEvent);
		return Mono.just(new AcquireCustomerResponse("ok")); 
	}

	public Mono<UpdateCustomerResponse> updateCustomer(UpdateCustomerCommand command) {
		
		return Mono.just(new UpdateCustomerResponse("ok")); 
	}

	public Mono<ReleaseCustomerResponse> removeCustomer(String identity,String conversationId) {
		var customerReleasedEvent = new CustomerReleasedEvent(conversationId,1,identity);
		customerEventRepository.save(customerReleasedEvent);
		rabbitTemplate.convertAndSend("custeventsexc", null, customerReleasedEvent);
		return Mono.just(new ReleaseCustomerResponse("ok")); 
	}

}
