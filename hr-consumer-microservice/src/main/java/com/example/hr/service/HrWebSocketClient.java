package com.example.hr.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

@Service
public class HrWebSocketClient implements WebSocketHandler {
	private final String hrServiceWsUrl;
	private final WebSocketClient webSocketClient;
	
	public HrWebSocketClient(@Value("${hrWsUrl}") String hrServiceWsUrl,
			WebSocketClient webSocketClient) {
		this.hrServiceWsUrl = hrServiceWsUrl;
		this.webSocketClient = webSocketClient;
	}

	@PostConstruct
	public void init() {
		webSocketClient.doHandshake(this,hrServiceWsUrl);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the WebSocket server.");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println("New event has arrived: "+message.getPayload().toString());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("Error while recieving message: "+e.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("WebSocket connection is closed.");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	} 
}
