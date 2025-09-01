package com.subrutin.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class PasswordEncryptedTest {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void encryptPassword() {
		log.info("password {}", passwordEncoder.encode("test123"));
	}
}