package com.subrutin.catalog.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.subrutin.catalog.domain.AppUser;

@SpringBootTest
public class AppUserRepositoryTest {
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Test
	public void testSave() {
		AppUser appUser = new AppUser();
		appUser.setName("Tedy");
		appUser.setMobileNumber("081234567890");
		appUser.setEmail("tedy@saputro.dev");
		
		appUserRepository.save(appUser);
	}

}
