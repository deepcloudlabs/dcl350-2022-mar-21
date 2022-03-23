package com.example.application;

import java.util.concurrent.TimeUnit;

import com.example.service.BusinessService;

public class BusinessApplication {

	public static void main(String[] args) {
		var srv = new BusinessService();
		for (var i=1;i<=10;++i) {
		   srv.fun().thenAcceptAsync( u -> {
			  System.err.println(Thread.currentThread().getName()+": Result is "+u); 
		   });
		}
		System.err.println("for loop has ended!");
		for (var i=0;i<20;++i) {
			try { TimeUnit.SECONDS.sleep(1);}catch(Exception e) {}
			System.err.println(Thread.currentThread().getName()+": Main is working hard "+i);
		}
		System.err.println("Application is done.");
	}

}
