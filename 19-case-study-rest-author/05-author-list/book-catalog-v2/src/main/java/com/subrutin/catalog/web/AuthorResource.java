package com.subrutin.catalog.web;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.AuthorCreateRequestDTO;
import com.subrutin.catalog.dto.AuthorDetailResponseDTO;
import com.subrutin.catalog.dto.AuthorListResponseDTO;
import com.subrutin.catalog.service.AuthorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController 
@RequestMapping("/v1/authors")
public class AuthorResource {
	
	private final AuthorService authorService;
	
	@PostMapping
	public ResponseEntity<Void> createAuthor(@RequestBody AuthorCreateRequestDTO dto){
		authorService.createAuthor(dto);
		return ResponseEntity.created(URI.create("/v1/authors")).build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AuthorDetailResponseDTO> findAuthorDetail(@PathVariable Long id){
		AuthorDetailResponseDTO dto= authorService.findAuthor(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<AuthorListResponseDTO>> findAuthorList(
			@RequestParam(required = false) String authorName){
		List<AuthorListResponseDTO> dtoList = authorService.findAuthorList(authorName);
		return ResponseEntity.ok(dtoList);
	}

}
