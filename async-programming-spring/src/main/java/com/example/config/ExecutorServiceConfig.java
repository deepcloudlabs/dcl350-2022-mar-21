package com.example.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExecutorServiceConfig {
	@Bean("my-pool")
	public ExecutorService exec1() {
		return Executors.newFixedThreadPool(100);
	}
}
