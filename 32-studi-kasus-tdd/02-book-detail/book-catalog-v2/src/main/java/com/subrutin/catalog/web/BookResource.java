package com.subrutin.catalog.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.BookCreateRequestDTO;
import com.subrutin.catalog.dto.BookDetailResponseDTO;
import com.subrutin.catalog.dto.IdResponseDTO;
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
	
}
