package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class BusinessService {
	public CompletableFuture<Integer> fun() { // asynchronous
		return CompletableFuture.supplyAsync(() -> {
			try { TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3, 10));}catch(Exception e) {}
			return 42;			
		});
	}
	public int gun() { // synchronous
		try { TimeUnit.SECONDS.sleep(3);}catch(Exception e) {}
		return 42;
	}
}
