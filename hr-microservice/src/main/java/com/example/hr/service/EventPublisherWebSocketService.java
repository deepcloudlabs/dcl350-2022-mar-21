package com.example.hr.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.hr.application.business.events.EmployeeEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventPublisherWebSocketService implements WebSocketHandler {
	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
	private final ObjectMapper mapper;
	
	public EventPublisherWebSocketService(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@EventListener
	public void publish(EmployeeEvent event) {
		sessions.entrySet().parallelStream()
		.forEach(entry -> {
			try {
				var eventAsJson = mapper.writeValueAsString(event) ;
				var eventAsTextMessage = new TextMessage(eventAsJson );
				entry.getValue().sendMessage(eventAsTextMessage );
			}catch (Exception e) {
				System.err.println("Error while converting to JSON: "+e.getMessage());
			}
		});
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.put(session.getId(), session);
		System.err.println("New session is created: "+session.getId());
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("Error in ws connection: "+e.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		sessions.remove(session.getId());		
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
}
