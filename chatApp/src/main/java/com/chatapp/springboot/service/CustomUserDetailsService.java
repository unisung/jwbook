package com.chatapp.springboot.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chatapp.springboot.model.UserEntity;
import com.chatapp.springboot.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	 private final UserRepository userRepository; //초기화 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//db로 부터 사용자 정보 얻기
		UserEntity user = userRepository
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
		//UserDetails정보로 변환하여 리턴
		return new User(user.getUsername(), user.getPassword(), 
				Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
				); // ROLE_USER, ROLE_ADMIN
	}
}
