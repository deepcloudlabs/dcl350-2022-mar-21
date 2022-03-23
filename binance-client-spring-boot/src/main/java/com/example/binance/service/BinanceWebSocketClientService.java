package com.example.binance.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import com.example.binance.dto.Trade;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BinanceWebSocketClientService implements WebSocketHandler {
	@Value("${binanceWebSocketUrl}")
	private String binanceWebSocketUrl;

	private WebSocketClient webSocketClient;
	private ObjectMapper objectMapper;

	public BinanceWebSocketClientService(WebSocketClient webSocketClient, ObjectMapper objectMapper) {
		this.webSocketClient = webSocketClient;
		this.objectMapper = objectMapper;
	}

	@PostConstruct
	public void connectToBinance() {
		webSocketClient.doHandshake(this, binanceWebSocketUrl);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the binance websocket server!");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		var trade = objectMapper.readValue(message.getPayload().toString(), Trade.class);
		System.err.println(trade);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("An error has occured in session (" + session.getId() + "): " + e.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Disconnected from the binance websocket server!");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
