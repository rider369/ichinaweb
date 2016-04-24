package com.ichinaweb.controller;

import org.springframework.stereotype.Component;

@Component
public class TestController {
	static{
		System.out.println("TestController static...");
	}
	
	public TestController() {
		System.out.println("TestController constructor...");
	}
	
	public void method() {
		System.out.println("TestController method...");
	}
}
