package com.subrutin.catalog.web;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.subrutin.catalog.dto.CategoryCreateRequestDTO;
import com.subrutin.catalog.dto.CategoryUpdateRequestDTO;
import com.subrutin.catalog.repository.CategoryRepository;

import io.restassured.http.ContentType;


public class CategoryResourceTest extends AbstractTest {

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	
	@Test
	void testCreateCategory_Success() {
		CategoryCreateRequestDTO dto = new CategoryCreateRequestDTO(
				"history", 
				"Sejarah", 
				"Buku - buku terkait sejarah indonesia dan dunia");
		long initSize =  categoryRepository.count();
		givenWithAuth()
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
		givenWithAuth()
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
		givenWithAuth()
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
		
		givenWithAuth()
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
		
		givenWithAuth()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(updateDTO)
	.when()
		.put("/v1/categories/notfound")
	.then()
		.statusCode(404)
		.time(Matchers.lessThan(1000L));		
		
	}
	
	@Test
	public void testDeleteCategory_Success() {
		CategoryCreateRequestDTO dto = new CategoryCreateRequestDTO(
				"non-fiction", 
				"Non Fiksi", 
				"Kategori non-fiksi mencakup karya-karya yang berdasarkan fakta, peristiwa nyata, dan informasi faktual");
		givenWithAuth()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(dto)
		.when()
			.post("/v1/categories")
		.then()
			.statusCode(201)
			.time(Matchers.lessThan(1000L));	
		
		long initSize =  categoryRepository.count();
		
		givenWithAuth()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
	.when()
		.delete("/v1/categories/non-fiction")
	.then()
		.statusCode(204)
		.time(Matchers.lessThan(1000L));
		
		assertEquals(initSize-1, categoryRepository.count());
		
	}
	
	
	@Test
	public void testDeleteCategory_NotFound() {
		givenWithAuth()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
	.when()
		.delete("/v1/categories/not-found")
	.then()
		.statusCode(404)
		.time(Matchers.lessThan(1000L));		
	}
	
	
	
	
	
	
	
	
}
