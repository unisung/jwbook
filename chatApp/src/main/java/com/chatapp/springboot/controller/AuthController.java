package com.chatapp.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.chatapp.springboot.dto.UserRegisterDTO;
import com.chatapp.springboot.model.UserEntity;
import com.chatapp.springboot.repository.UserRepository;



//사용자 관리 Controller
@Controller
public class AuthController {
	//패스워드 암호화 객체
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//사용자정보 처리 객체
	@Autowired
	private UserRepository userRepository;

	//index페이지
	@GetMapping("/")
	public String root(){
		return "index";
	}
	//로그인 페이지
	@GetMapping("/login")
	public String loginPage() {
		return "login"; // login.jsp
	}
	//회원가입 페이지
	@GetMapping("/register")
	public String registerForm() {
		return "register"; //register.jsp
	}
	
	//회원등록 처리
	@PostMapping("/register")
	public String register(UserRegisterDTO dto) {
		//회원등록처리시 기존에 존재하는  id면 에러처리
		if(userRepository.findByUsername(dto.getUsername()).isPresent()) {
			return "redirect:/register?error";
		}
		//기존회원이 아니면 등록처리
		UserEntity user = UserEntity.builder()
				                    .username(dto.getUsername())
				                    .password(passwordEncoder.encode(dto.getPassword()))
				                    .role("USER")
				                    .build();
		//db에 저장
		userRepository.save(user);
		//저장 후 login페이지로 이동
		return "redirect:/login";
	}
	
	
	

}
