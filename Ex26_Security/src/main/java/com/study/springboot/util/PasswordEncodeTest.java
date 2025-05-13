package com.study.springboot.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncodeTest {
	public static void main(String[] args) {
		PasswordEncoder encoder 
		 = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encoded = encoder.encode("1234");
		System.out.println("암호화된 '1234' => " + encoded);
	}
}
