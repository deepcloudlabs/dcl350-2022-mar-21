package com.example.hr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.hr.service.EventPublisherWebSocketService;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

	private final EventPublisherWebSocketService eventPublisherWebSocketService;

	public WebSocketConfig(EventPublisherWebSocketService eventPublisherWebSocketService) {
		this.eventPublisherWebSocketService = eventPublisherWebSocketService;
	}
    // ws://localhost:8100/hr/api/v1/events
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(eventPublisherWebSocketService, "/events")
		        .setAllowedOrigins("*");
	}

	
}
