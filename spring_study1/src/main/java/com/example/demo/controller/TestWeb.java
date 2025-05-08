package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.MyDTO;

import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/test")
public class TestWeb { //POJO
	
	@GetMapping("/hello")
	public String hello() {
		return "hello"; // WEB-INF/views/ + "hello" + .jsp
	}
	
	
	@GetMapping("/hello2") // /test/hello2?msg=hi,  /test/hello2
	@ResponseBody
	public String hello2(@RequestParam(value="msg", required = false) String msg) {
		return msg;
	}
	
	@GetMapping("/hello3/{msg}")
	public String hello3(@PathVariable  String msg, Model m) {
		m.addAttribute("msg", msg);//view로 객체 전달
		return "hello";
	}
	
	
	@GetMapping("/hello33/{id}/{name}/{password}/{age}")
	@ResponseBody
	public String hello33(@PathVariable  String id, 
			@PathVariable  String name, 
			@PathVariable  String password, 
			@PathVariable  String age) {
		
		return "id:"+id+",name:"+name+",age:"+age;
	}
	
	@GetMapping("/hello4") // /test/hello4?id=hong&name=홍길동&password=1234&age=18
	@ResponseBody
	public String hello4(@RequestParam(value="id",  required = true) String id,
						 @RequestParam(value="name", required = true) String name,
						 @RequestParam(value="password", required = true) String password,
						 @RequestParam(value="age", required = false) String age
						) {
		return "id:"+id+", name:"+name+",age:"+age;
	}
	
	@GetMapping("/hello5") // /test/hello4?id=hong&name=홍길동&password=1234&age=18
	@ResponseBody
	public MyDTO hello5(MyDTO dto) {
		return dto;
	}
	
	
}
