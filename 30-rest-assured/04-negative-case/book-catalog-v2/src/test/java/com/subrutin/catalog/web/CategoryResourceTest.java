package com.subrutin.catalog.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import com.subrutin.catalog.dto.CategoryCreateRequestDTO;
import com.subrutin.catalog.repository.CategoryRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CategoryResourceTest {
	
	@LocalServerPort
	private Integer port;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:"+port;
	}
	
	@Test
	void testCreateCategory_Success() {
		CategoryCreateRequestDTO dto = new CategoryCreateRequestDTO(
				"history", 
				"Sejarah", 
				"Buku - buku terkait sejarah indonesia dan dunia");
		long initSize =  categoryRepository.count();
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(dto)
		.when()
			.post("/v1/categories")
		.then()
			.statusCode(201)
			.time(Matchers.lessThan(1000L));
		
		assertEquals(initSize+1, categoryRepository.count());
	}
	
	
	@Test
	void testCreateCategory_ValidationError() {
		CategoryCreateRequestDTO dto = new CategoryCreateRequestDTO(
				"", 
				"Sejarah", 
				"Buku - buku terkait sejarah indonesia dan dunia");	
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(dto)
		.when()
			.post("/v1/categories")
		.then()
			.statusCode(400)
			.time(Matchers.lessThan(1000L));		
		
	}
}
