package com.example.lottery.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@RefreshScope
public class RestConfig {
	@Value("${server.port}")
	private int port;
	
	
}
