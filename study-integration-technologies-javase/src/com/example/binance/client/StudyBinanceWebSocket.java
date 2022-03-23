package com.example.binance.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

// Event-triggered: Domain/Business Event
// Asynchronous Programming --> Observer
// Request/Response, Fire/Forget, Publish/Subscribe -> Pull + Push
// Text + Binary, Can streaming
// Domain Event -> Json + [Near] Real-time -> 5G
// http/3 -> WS
// IoT, Cloud Edge -> MQTT/WS
public class StudyBinanceWebSocket {
	private static final String BINANCE_REST_WS_API = "wss://stream.binance.com:9443/ws/btcusdt@trade";

	public static void main(String[] args) throws InterruptedException {
		var listener = new BinanceWebSocketListener();
		HttpClient.newHttpClient()
				  .newWebSocketBuilder()
				  .buildAsync(URI.create(BINANCE_REST_WS_API), listener );
		TimeUnit.SECONDS.sleep(20);
	}

}

// Listener/Observer
class BinanceWebSocketListener implements Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connected to the binance server!");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(data);
		return Listener.super.onText(webSocket, data, last);
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Disconnected from the binance server!");
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("An error has occurred:"+error.getMessage());
	}
	
}
