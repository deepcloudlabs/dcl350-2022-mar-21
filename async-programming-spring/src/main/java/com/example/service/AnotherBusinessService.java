package com.example.service;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AnotherBusinessService {

	@Autowired
	private BusinessService businessService;
	@Autowired
	@Qualifier("my-pool")
	private ExecutorService executorService;
	
	@Scheduled(fixedRate = 6_000)
	public void callAsyncMethod() {
		businessService.fun().thenAcceptAsync( value -> {
			System.err.println(Thread.currentThread().getName()+": "+value);
			
		},executorService);
	}
}
