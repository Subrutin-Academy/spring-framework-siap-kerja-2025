package com.subrutin.catalog.web;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import com.subrutin.catalog.dto.BookCreateRequestDTO;
import com.subrutin.catalog.dto.BookUpdateRequestDTO;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class BookResourceTest extends AbstractTest{
	  	@Test
	    void createBook_Success(){
	        //create payload book 
	        BookCreateRequestDTO requestDTO = new BookCreateRequestDTO(
	            "Einstein: His Life and Universe",
	            "Einstein: His Life and Universe adalah biografi komprehensif tentang Albert Einstein yang ditulis oleh Walter Isaacson. Buku ini mengeksplorasi tidak hanya pencapaian ilmiah Einstein yang revolusioner, tetapi juga kehidupan pribadinya yang kompleks, pandangan politiknya, dan kepribadiannya yang unik. Isaacson menggambarkan perjalanan Einstein dari masa kecilnya yang pemberontak hingga menjadi ikon ilmiah dunia. Buku ini menjelaskan bagaimana kreativitas dan imajinasinya yang luar biasa memungkinkannya mengembangkan teori relativitas dan kontribusi penting lainnya pada fisika modern. Isaacson juga mengeksplorasi hubungan Einstein dengan istri-istrinya, anak-anaknya, dan koleganya, serta keterlibatannya dalam isu-isu sosial dan politik seperti pacifisme, Zionisme, dan perlucutan senjata nuklir. Berdasarkan arsip pribadi Einstein yang baru dibuka dan penelitian ekstensif, biografi ini memberikan potret mendalam tentang salah satu pikiran paling brilian dan berpengaruh dalam sejarah manusia",
	            704,
	            2007,
	            16L,
	            List.of(16L),
	            List.of("biography")
	        );

	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	            .body(requestDTO)
	        .when()
	            .post("/v1/books")
	        .then()
	            .statusCode(200);
	    }

	    //negative case invalid author
	    @Test
	    void createBook_InvalidAuthor() {
	        //create payload book 
	        BookCreateRequestDTO requestDTO = new BookCreateRequestDTO(
	            "Einstein: His Life and Universe",
	            "Einstein: His Life and Universe adalah biografi komprehensif tentang Albert Einstein yang ditulis oleh Walter Isaacson. Buku ini mengeksplorasi tidak hanya pencapaian ilmiah Einstein yang revolusioner, tetapi juga kehidupan pribadinya yang kompleks, pandangan politiknya, dan kepribadiannya yang unik. Isaacson menggambarkan perjalanan Einstein dari masa kecilnya yang pemberontak hingga menjadi ikon ilmiah dunia. Buku ini menjelaskan bagaimana kreativitas dan imajinasinya yang luar biasa memungkinkannya mengembangkan teori relativitas dan kontribusi penting lainnya pada fisika modern. Isaacson juga mengeksplorasi hubungan Einstein dengan istri-istrinya, anak-anaknya, dan koleganya, serta keterlibatannya dalam isu-isu sosial dan politik seperti pacifisme, Zionisme, dan perlucutan senjata nuklir. Berdasarkan arsip pribadi Einstein yang baru dibuka dan penelitian ekstensif, biografi ini memberikan potret mendalam tentang salah satu pikiran paling brilian dan berpengaruh dalam sejarah manusia",
	            704,
	            2007,
	            16L,
	            List.of(9999L),//author not found
	            List.of("biography")
	        );

	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	            .body(requestDTO)
	        .when()
	            .post("/v1/books")
	        .then()
	            .statusCode(404);
	    }

	    //negative case invalid category
	    @Test
	    void createBook_InvalidCategory() {
	        //create payload book 
	        BookCreateRequestDTO requestDTO = new BookCreateRequestDTO(
	            "Einstein: His Life and Universe",
	            "Einstein: His Life and Universe adalah biografi komprehensif tentang Albert Einstein yang ditulis oleh Walter Isaacson. Buku ini mengeksplorasi tidak hanya pencapaian ilmiah Einstein yang revolusioner, tetapi juga kehidupan pribadinya yang kompleks, pandangan politiknya, dan kepribadiannya yang unik. Isaacson menggambarkan perjalanan Einstein dari masa kecilnya yang pemberontak hingga menjadi ikon ilmiah dunia. Buku ini menjelaskan bagaimana kreativitas dan imajinasinya yang luar biasa memungkinkannya mengembangkan teori relativitas dan kontribusi penting lainnya pada fisika modern. Isaacson juga mengeksplorasi hubungan Einstein dengan istri-istrinya, anak-anaknya, dan koleganya, serta keterlibatannya dalam isu-isu sosial dan politik seperti pacifisme, Zionisme, dan perlucutan senjata nuklir. Berdasarkan arsip pribadi Einstein yang baru dibuka dan penelitian ekstensif, biografi ini memberikan potret mendalam tentang salah satu pikiran paling brilian dan berpengaruh dalam sejarah manusia",
	            704,
	            2007,
	            16L,
	            List.of(9999L),
	            List.of("not-found-category")//category not found
	        );

	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	            .body(requestDTO)
	        .when()
	            .post("/v1/books")
	        .then()
	            .statusCode(404);
	    }

	    //negative case invalid publisher
	    @Test
	    void createBook_InvalidPublisher() {
	        //create payload book 
	        BookCreateRequestDTO requestDTO = new BookCreateRequestDTO(
	            "Einstein: His Life and Universe",
					"Einstein: His Life and Universe adalah biografi komprehensif tentang Albert Einstein yang ditulis oleh Walter Isaacson. Buku ini mengeksplorasi tidak hanya pencapaian ilmiah Einstein yang revolusioner, tetapi juga kehidupan pribadinya yang kompleks, pandangan politiknya, dan kepribadiannya yang unik. Isaacson menggambarkan perjalanan Einstein dari masa kecilnya yang pemberontak hingga menjadi ikon ilmiah dunia. Buku ini menjelaskan bagaimana kreativitas dan imajinasinya yang luar biasa memungkinkannya mengembangkan teori relativitas dan kontribusi penting lainnya pada fisika modern. Isaacson juga mengeksplorasi hubungan Einstein dengan istri-istrinya, anak-anaknya, dan koleganya, serta keterlibatannya dalam isu-isu sosial dan politik seperti pacifisme, Zionisme, dan perlucutan senjata nuklir. Berdasarkan arsip pribadi Einstein yang baru dibuka dan penelitian ekstensif, biografi ini memberikan potret mendalam tentang salah satu pikiran paling brilian dan berpengaruh dalam sejarah manusia",
		            704,
	            2007,
	            9999L,
	            List.of(16L),
	            List.of("biography")
	        );

	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	            .body(requestDTO)
	        .when()
	            .post("/v1/books")
	        .then()
	            .statusCode(404);
	    }
	    
	    //test case book detail
	    @Test
	    void testFindBookDetail() {
	        //v1/books/{id}
	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	        .when()
	            .get("/v1/books/1")
	        .then()
	            .statusCode(200)
	            .body("title", Matchers.notNullValue())
	            .time(Matchers.lessThan(1000L));
	    }

	    //test case book detail
	    @Test
	    void testFindBookDetail_InvalidId() {
	        //v1/books/{id}
	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	        .when()
	            .get("/v1/books/9999")
	        .then()
	            .statusCode(404)
	            .time(Matchers.lessThan(1000L));
	    }
	    
	    
	    
	    //test update book
	    @Test
	    void testUpdateBook_Success() {
	        //v1/books/{id}
	        BookUpdateRequestDTO dto = new BookUpdateRequestDTO(
	            "The Innovators - Edited",
	            "The Innovators adalah sebuah karya komprehensif yang mengisahkan sejarah revolusi digital dan orang-orang di baliknya. Walter Isaacson dengan teliti menelusuri evolusi komputer dan internet, dimulai dari Ada Lovelace di abad ke-19 yang menuliskan konsep pemrograman komputer pertama", //edit description
	            542,
	            2014,
	            16L,
	            List.of(16L), 
	            List.of("BIOGRAPHY","SEJARAH", "TEKNOLOGI") //edit category
	        );
	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	            .body(dto)
	        .when()
	            .put("/v1/books/25")
	        .then()
	            .statusCode(200)
	            .time(Matchers.lessThan(1000L));

	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	        .when()
	            .get("/v1/books/25")
	        .then()
	            .statusCode(200)
	            .body("title", Matchers.equalTo("The Innovators - Edited"))
	            .body("categories", Matchers.hasSize(3))
	            .time(Matchers.lessThan(1000L));
	    }
	    


	    //negative case update book
	    @Test
	    void testUpdateBook_InvalidPublisher() {
	        //v1/books/{id}
	        BookUpdateRequestDTO dto = new BookUpdateRequestDTO(
	            "The Innovators - Edited",
	            "The Innovators adalah sebuah karya komprehensif yang mengisahkan sejarah revolusi digital dan orang-orang di baliknya. Walter Isaacson dengan teliti menelusuri evolusi komputer dan internet, dimulai dari Ada Lovelace di abad ke-19 yang menuliskan konsep pemrograman komputer pertama", //edit description
	            542,
	            2014,
	            9999L,
	            List.of(16L), 
	            List.of("BIOGRAPHY","SEJARAH", "TEKNOLOGI") //edit category
	        );
	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	            .body(dto)
	        .when()
	            .put("/v1/books/25")
	        .then()
	            .statusCode(404)
	            .time(Matchers.lessThan(1000L));
	    }

	    //negative case update book
	    @Test
	    void testUpdateBook_InvalidCategory() {
	        //v1/books/{id}
	        BookUpdateRequestDTO dto = new BookUpdateRequestDTO(
	            "The Innovators - Edited",
	            "The Innovators adalah sebuah karya komprehensif yang mengisahkan sejarah revolusi digital dan orang-orang di baliknya. Walter Isaacson dengan teliti menelusuri evolusi komputer dan internet, dimulai dari Ada Lovelace di abad ke-19 yang menuliskan konsep pemrograman komputer pertama", //edit description
	            542,
	            2014,
	            19L,
	            List.of(16L), 
	            List.of("BIOGRAPHY","SEJARAH", "TEKNOLOGI", "NOT-FOUND") //edit category
	        );
	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	            .body(dto)
	        .when()
	            .put("/v1/books/25")
	        .then()
	            .statusCode(404)
	            .time(Matchers.lessThan(1000L));
	    }

	    //negative case update book blank authorId
	    @Test
	    void testUpdateBook_BlankAuthorId() {
	        //v1/books/{id}
	        BookUpdateRequestDTO dto = new BookUpdateRequestDTO(
	            "The Innovators - Edited",
	            "The Innovators adalah sebuah karya komprehensif yang mengisahkan sejarah revolusi digital dan orang-orang di baliknya. Walter Isaacson dengan teliti menelusuri evolusi komputer dan internet, dimulai dari Ada Lovelace di abad ke-19 yang menuliskan konsep pemrograman komputer pertama", //edit description
	            542,
	            2014,
	            19L,
	            List.of(), 
	            List.of("BIOGRAPHY","SEJARAH", "TEKNOLOGI") //edit category
	        );
	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	            .body(dto)
	        .when()
	            .put("/v1/books/25")
	        .then()
	            .statusCode(400)
	            .time(Matchers.lessThan(1000L));	    }

	    //negative case update book blank publisherId
	    @Test
	    void testUpdateBook_BlankPublisherId() {
	        //v1/books/{id}
	        BookUpdateRequestDTO dto = new BookUpdateRequestDTO(
	            "The Innovators - Edited",
	            "The Innovators adalah sebuah karya komprehensif yang mengisahkan sejarah revolusi digital dan orang-orang di baliknya. Walter Isaacson dengan teliti menelusuri evolusi komputer dan internet, dimulai dari Ada Lovelace di abad ke-19 yang menuliskan konsep pemrograman komputer pertama", //edit description
	            542,
	            2014,
	            null,
	            List.of(16L), 
	            List.of("BIOGRAPHY","SEJARAH", "TEKNOLOGI") //edit category
	        );
	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	            .body(dto)
	        .when()
	            .put("/v1/books/25")
	        .then()
	            .statusCode(400)
	            .time(Matchers.lessThan(1000L));	   
	        
	    }

	    //negative case update book blank categoryId
	    @Test
	    void testUpdateBook_BlankCategoryId() {
	        //v1/books/{id}
	        BookUpdateRequestDTO dto = new BookUpdateRequestDTO(
	            "The Innovators - Edited",
	            "The Innovators adalah sebuah karya komprehensif yang mengisahkan sejarah revolusi digital dan orang-orang di baliknya. Walter Isaacson dengan teliti menelusuri evolusi komputer dan internet, dimulai dari Ada Lovelace di abad ke-19 yang menuliskan konsep pemrograman komputer pertama", //edit description
	            542,
	            2014,
	            19L,
	            List.of(16L), 
	            List.of() //edit category
	        );
	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	            .body(dto)
	        .when()
	            .put("/v1/books/25")
	        .then()
	            .statusCode(400)
	            .time(Matchers.lessThan(1000L));	   
	        
	    }	    
	    

	    //test case for delete book
	    @Test
	    void testDeleteBook_Success() {
	        //v1/books/{id}
	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	        .when()
	            .delete("/v1/books/24")
	        .then()
	            .statusCode(204)
	            .time(Matchers.lessThan(1000L));	   

	        //check book is deleted
	        given()
            	.contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	        .when()
	            .get("/v1/books/24")
	        .then()
	            .statusCode(404)
	            .time(Matchers.lessThan(1000L));	   

	    }

	    //negative case delete book
	    @Test
	    void testDeleteBook_InvalidId() {
	        //v1/books/{id}
	        given()
	            .contentType(ContentType.JSON)
	            .accept(ContentType.JSON)
	        .when()
	            .delete("/v1/books/9999")
	        .then()
	            .statusCode(404)
	            .time(Matchers.lessThan(1000L));	   
	    }
	    
	    
}
