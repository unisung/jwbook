package com.example.demo;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestRestController { //POJO
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value="msg", required = false) String msg) {
		return msg;
	}
	
	@GetMapping("/hello2")
	public HashMap<String, String> hello3(){
		HashMap<String, String>map = new HashMap<>();
		map.put("이름", "홍길동");
		map.put("나이", "30");
		map.put("국적", "서울");
		return map;
	}

}
