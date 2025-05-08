package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestWeb { //POJO
	
	@GetMapping("/hello")
	public String hello() {
		return "hello"; // WEB-INF/views/ + "hello" + .jsp
	}
	
}
