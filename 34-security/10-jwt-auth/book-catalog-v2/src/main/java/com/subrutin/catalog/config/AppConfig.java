package com.subrutin.catalog.config;

import java.security.Key;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subrutin.catalog.security.util.JwtTokenFactory;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Configuration
public class AppConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public SecretKey key() {
		byte[] keyBytes = Decoders.BASE64.decode("adsjkdjskljqwqwqdjksakldjakjskaljdssdckjuqwoiueoiujweqiojqwewqe");
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	@Bean
	public JwtTokenFactory jwtTokenFactory(Key secret) {
		return new JwtTokenFactory(secret);
	}
	

}
