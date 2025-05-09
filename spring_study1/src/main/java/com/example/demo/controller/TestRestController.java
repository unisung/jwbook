package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MyDTO;

@RestController
@RequestMapping("/api")
public class TestRestController {
	
	@GetMapping("/hello")  /* http://localhost:8080/api/hello?msg=안녕하세요 */
	public String hello(@RequestParam(value="msg", required = false) String msg) {
		return msg;
	}
	
	@GetMapping("/hello2")
	public Map<String, String> hello2(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("id", "hong");
		map.put("name", "홍길동");
		map.put("age", "18");
		
		return map;
	}
	
	@GetMapping("/hello3")
	public MyDTO hello3(){
		MyDTO dto = new MyDTO();
		dto.setId("hong");
		dto.setName("홍길동");
		dto.setAge(18);
		
		return dto;
	}
	
	
	
}
