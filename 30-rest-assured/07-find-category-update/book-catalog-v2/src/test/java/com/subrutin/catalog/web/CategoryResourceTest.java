package com.subrutin.catalog.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.Matcher;
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
import com.subrutin.catalog.dto.CategoryUpdateRequestDTO;
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
	
	@Test
	public void testFindCategoryList() {
		given()
			.when()
			.get("/v1/categories")
		.then()
			.statusCode(200)
			.body("result", notNullValue())
			.body("result[0].code", notNullValue())
			.body("pages", notNullValue())
			.body("elements", notNullValue());

	}
	
	@Test
	public void testFindCategoryListWithPagination() {
		given()
		.queryParam("pages", 0)
		.queryParam("limit", 2)
		.when()
		.get("/v1/categories")
	.then()
		.statusCode(200)
		.body("result", Matchers.hasSize(2));
	}
	
	
	@Test
	public void testFindCategoryListWithSearch() {
		given()
		.queryParam("categoryName", "nov")
		.when()
		.get("/v1/categories")
	.then()
		.statusCode(200)
		.body("result[0].name", equalTo("Novel"));
	}	
	
	@Test
	public void testFindCategoryListWithSorting() {
		given()
		.queryParam("sortBy", "name")
		.queryParam("direction", "desc")
		.when()
		.get("/v1/categories")
	.then()
		.statusCode(200)
		.body("result", notNullValue());
	}		
	
	
	@Test
	public void testGetCategoryDetail_Success() {
		given()
			.when()
			.get("/v1/categories/novel")
		.then()
			.statusCode(200)
			.body("code", equalTo("NOVEL"))
			.body("name", equalTo("Novel"))
			.body("description", equalTo("Karya fiksi prosa panjang yang mengandung rangkaian cerita kehidupan "
					+ "seseorang dengan orang-orang di sekelilingnya"));

	}	
	
	@Test
	public void testGetCategoryDetail_NotFound() {
		given()
			.when()
			.get("/v1/categories/non-existent-categories")
		.then()
			.statusCode(404);

	}	
	
	
	@Test
	public void testUpdateCategory_Success() {
		//create a category
		CategoryCreateRequestDTO dto = new CategoryCreateRequestDTO(
				"scifi", 
				"Fiksi Ilmiah", 
				"Karya Fiksi yang mengeksplorasi konsep futuristik, sains, dan teknologi");			
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(dto)
		.when()
			.post("/v1/categories")
		.then()
			.statusCode(201);
		//update 
		
		CategoryUpdateRequestDTO updateDTO = new CategoryUpdateRequestDTO(
				"Fiksi-Ilmiah", "Karya Fiksi yang mengeksplorasi konsep futuristik, sains, dan teknologi");
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(updateDTO)
		.when()
			.put("/v1/categories/scifi")
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(1000L));

	}	
	
	
	@Test
	public void testUpdateCategory_NotFound() {
		CategoryUpdateRequestDTO updateDTO = new CategoryUpdateRequestDTO(
				"Sejarah - edited", 
				"Buku - buku terkait sejarah indonesia dan dunia");
		
		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(updateDTO)
	.when()
		.put("/v1/categories/notfound")
	.then()
		.statusCode(404)
		.time(Matchers.lessThan(1000L));		
		
	}
	
	
	
	
	
	
	
}
