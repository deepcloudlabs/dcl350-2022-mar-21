package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
	@Autowired
	@Qualifier("my-pool")
	private ExecutorService executorService;
	
	@Async 
	public CompletableFuture<Integer> fun() { // asynchronous
		return CompletableFuture.supplyAsync(() -> {
			System.err.println(Thread.currentThread().getName()+" is running BusinessService::fun");
			try { TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 3));}catch(Exception e) {}
			return 42;
		}, executorService);			
	}
}
