package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/test")
public class TestWebController {//POJO
	
	@GetMapping("/hello")
	public String hello() {
		return "hello"; // /WEB-INF/views/ + hello + .jsp 
		                //   -> /WEB-INF/views/hello.jsp
	}
	
	@GetMapping("/hello2")  // http://localhost:8080/test/hello2?name=홍길동
	@ResponseBody// RESTful
	public String hello2(@RequestParam(value = "name", required = false)String name) {
		return name+"님 안녕하세요"; // String name = request.getParameter("name");            
	}
	
	@GetMapping("/hello3/{msg}")  // http://localhost:8080/test/hello3/안녕하세요
	public String hello3(@PathVariable String msg, Model m) {
		m.addAttribute("msg", msg);
		return "hello"; //   -> /WEB-INF/views/hello.jsp
	}
	
	
}
