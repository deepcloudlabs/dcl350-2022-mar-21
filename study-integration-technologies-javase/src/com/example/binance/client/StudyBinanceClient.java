package com.example.binance.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.TimeUnit;

// Rest over http
// 1) Http: Request/Response --> Pull
// 2) Http/2 -> SSE (Server Sent Event) -> Push Notification -> Text (json), cannot stream
public class StudyBinanceClient {
	private static final String BINANCE_REST_API = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		var httpClient = HttpClient.newBuilder().build();
		var request = HttpRequest.newBuilder().uri(URI.create(BINANCE_REST_API)).build();
		for (var i=0;i<10;++i) {
			var response = httpClient.send(request,BodyHandlers.ofString()).body();
			System.err.println(response);
			TimeUnit.MILLISECONDS.sleep(1);
		}
	}

}
