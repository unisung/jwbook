package com.study.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {

	@Autowired
	public AuthenticationFailureHandler authenticationFailureHandler;

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
				//.failureUrl("/loginError")   // default : /login?error
				.failureHandler(authenticationFailureHandler) // 핸들러로 변경
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
	
//	@Bean
//	public UserDetailsService users() {
//		UserDetails user = User.builder()
//				           .username("user")
//				           .password(PasswordEncoder().encode("1234"))
//				           .roles("USER")  //ROLE_USER 에서 ROLE_ 자동으로 붙음.
//				           .build();
//		
//		UserDetails admin = User.builder()
//		           .username("admin")
//		           .password(PasswordEncoder().encode("1234"))
//		           .roles("USER", "ADMIN")  //ROLE_USER, ROLE_ADMIN 에서 ROLE_ 자동으로 붙음.
//		           .build();
//		//메모리에 사용자 정보 담기
//		return new InMemoryUserDetailsManager(user,admin);
//	}
//
//	@Bean
//	public PasswordEncoder PasswordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
		    .dataSource(dataSource)
		    .usersByUsernameQuery("select name as userName, password, enabled from user_list where name = ?")
		    .authoritiesByUsernameQuery("select name as userName, authority from user_list where name = ?")
		    .passwordEncoder(new BCryptPasswordEncoder());
	}

}
