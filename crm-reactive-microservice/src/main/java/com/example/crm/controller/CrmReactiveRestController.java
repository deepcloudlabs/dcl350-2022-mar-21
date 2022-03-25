package com.example.crm.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.CustomerDocument;
import com.example.crm.service.CustomerReactiveService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
@CrossOrigin
@Validated
public class CrmReactiveRestController {
    private final CustomerReactiveService customerService;
	
	public CrmReactiveRestController(CustomerReactiveService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("{identity}")
	public Mono<CustomerDocument> getCustomerByIdentity(@PathVariable @NotBlank String identity) {
		return customerService.findCustomerById(identity);
	}
	
	@GetMapping
	public Flux<CustomerDocument> getCustomersByPage(
			@RequestParam @Min(0) int pageNo,
			@RequestParam @Max(50) int pageSize) {
		return customerService.findCustomers(pageNo,pageSize);
	}
	
	@PostMapping
	public Mono<CustomerDocument> addCustomer(@RequestBody CustomerDocument document){
		return customerService.createCustomer(document);
	}
	
	@PutMapping("{identity}")
	public Mono<CustomerDocument> updateCustomer(@PathVariable @NotBlank String identity,
			@RequestBody CustomerDocument document){
		return customerService.updateCustomer(identity,document);
	}
	
	@DeleteMapping("{identity}")
	public Mono<CustomerDocument> deleteCustomer(@PathVariable @NotBlank String identity){
		return customerService.removeCustomer(identity);
	}
}
