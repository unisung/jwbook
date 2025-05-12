package com.study.springboot.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {

//    private final UserDetailsService users;
//
//    WebSecurityConfig(UserDetailsService users) {
//        this.users = users;
//    }
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf((csrf) -> csrf.disable())
		    .cors(cors -> cors.disable())
		    .authorizeHttpRequests(request -> request
		    		.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
		    		.requestMatchers("/").permitAll()
		    		.requestMatchers("/css/**","/js/**","/img/**").permitAll()
		    		.requestMatchers("/guest/**").permitAll()
		    		.requestMatchers("/member/**").hasAnyRole("USER","ADMIN")
		    		.requestMatchers("/admin/").hasRole("ADMIN")
		    		.anyRequest().authenticated() //어떤한 요청이라도 인증필요
		    	);
		
		//로그인페이지 설정
		//http.formLogin(formLogin -> formLogin.permitAll());//기본 로그인 페이지
		// 사용자 정의 방식으로 변경
		http.formLogin(formLogin -> formLogin
				.loginPage("/loginForm")           //default : /login
				.loginProcessingUrl("/j_spring_security_check")
				.failureUrl("/loginError")   // default : /login?error
				.usernameParameter("j_username") // default : j_username
				.passwordParameter("j_password") // default : j_password
				.permitAll());
		//로그아웃 설정
		//http.logout(logout -> logout.permitAll()); //기본 로그아웃 설정 (/logout)
		http.logout(logout -> logout
				.logoutUrl("/logout") //default
				.logoutSuccessUrl("/")				
				.permitAll());
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder()
				           .username("user")
				           .password(PasswordEncoder().encode("1234"))
				           .roles("USER")  //ROLE_USER 에서 ROLE_ 자동으로 붙음.
				           .build();
		
		UserDetails admin = User.builder()
		           .username("admin")
		           .password(PasswordEncoder().encode("1234"))
		           .roles("USER", "ADMIN")  //ROLE_USER, ROLE_ADMIN 에서 ROLE_ 자동으로 붙음.
		           .build();
		//메모리에 사용자 정보 담기
		return new InMemoryUserDetailsManager(user,admin);
	}

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
