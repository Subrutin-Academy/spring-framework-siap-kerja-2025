package com.subrutin.catalog.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.BookCreateRequestDTO;
import com.subrutin.catalog.dto.BookDetailResponseDTO;
import com.subrutin.catalog.dto.BookListResponseDTO;
import com.subrutin.catalog.dto.BookUpdateRequestDTO;
import com.subrutin.catalog.dto.IdResponseDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;
import com.subrutin.catalog.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/books")
public class BookResource {
	
	private final BookService bookService;

	@PostMapping
	public ResponseEntity<IdResponseDTO> createBook(@RequestBody @Valid BookCreateRequestDTO dto){
		return ResponseEntity.ok(bookService.createBook(dto));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<BookDetailResponseDTO> findBookDetail(@PathVariable Long id){
		return ResponseEntity.ok(bookService.findBookDetail(id));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody @Valid BookUpdateRequestDTO dto){
		bookService.updateBook(id, dto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id){
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<ResultPageResponseDTO<BookListResponseDTO>> findBookList(
		@RequestParam(defaultValue = "0") Integer pages,
		@RequestParam(defaultValue = "10") Integer limit, 
		@RequestParam(defaultValue = "title") String sortBy, 
		@RequestParam(defaultValue = "asc") String direction, 
		@RequestParam(required = false) String bookTitle){
		return ResponseEntity.ok(bookService.findBookListUsingJpaProjection(pages, limit, sortBy, direction, bookTitle));
	}
	
}
