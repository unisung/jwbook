package com.chatapp.springboot.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.chatapp.springboot.service.CustomUserDetailsService;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf((csrf) -> csrf.disable())
		    .cors(cors -> cors.disable())
		    .authorizeHttpRequests(request -> request
		    		.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
		    		.requestMatchers("/","/login","/register","/ws/**","/css/**","/js/**","/img/**").permitAll()
		    		.anyRequest().authenticated() //어떤한 요청이라도 인증필요
		    	);
		
		//로그인페이지 설정
		//http.formLogin(formLogin -> formLogin.permitAll());//기본 로그인 페이지
		// 사용자 정의 방식으로 변경
		http.formLogin(formLogin -> formLogin
				.loginPage("/login")           //default : /login
				.defaultSuccessUrl("/chatrooms", true) //로그인 완료후 이동할 페이지 설정
				.permitAll());
		//로그아웃 설정
		//http.logout(logout -> logout.permitAll()); //기본 로그아웃 설정 (/logout)
		http.logout(logout -> logout
				.logoutSuccessUrl("/")				
				.permitAll());
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(customUserDetailsService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
