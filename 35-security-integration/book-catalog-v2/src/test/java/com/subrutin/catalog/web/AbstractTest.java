package com.subrutin.catalog.web;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.subrutin.catalog.dto.LoginRequestDTO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

@Testcontainers
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AbstractTest {
	
	@LocalServerPort
	private Integer port;
	
	static String jwtToken;
	
	protected RequestSpecification givenWithAuth() {
		return given()
			.contentType(ContentType.JSON)
			.header("Authorization","Bearer "+jwtToken);
	}
	
	protected String getJwtToken(String username, String password) {
		LoginRequestDTO loginRequestDTO = new LoginRequestDTO
				(username, password);
		
		Response response = given()
			.contentType(ContentType.JSON)
			.body(loginRequestDTO)
		.when()
			.post("/v1/login")
		.then()
			.statusCode(200)
			.extract()
			.response();
		
		return response.jsonPath().getString("result");
	}
	
	@Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
			.withDatabaseName("catalogdb-container")
			.withUsername("test")
			.withPassword("test");
	
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
		
	}
	
	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
		
		if(!StringUtils.isBlank(jwtToken)) {
			return;
		}
		jwtToken = getJwtToken("admin01", "test123");
	}
	
	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}
	
	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

}
