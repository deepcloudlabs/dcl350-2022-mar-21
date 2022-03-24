package com.example.lottery.config;

import org.springframework.beans.factory.annotation.Value;

//@Configuration
//@RefreshScope
public class RestConfig {
	@Value("${server.port}")
	private int port;
	
	
}
