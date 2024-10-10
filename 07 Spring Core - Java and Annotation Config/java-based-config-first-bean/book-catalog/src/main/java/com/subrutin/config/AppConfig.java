package com.subrutin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.subrutin.domain.Author;

@Configuration
public class AppConfig {

	@Bean
	public Author author1() {
		Author author = new Author();
		author.setId(1L);
		author.setName("Karen Armstrong");
		return author;
	}
}
