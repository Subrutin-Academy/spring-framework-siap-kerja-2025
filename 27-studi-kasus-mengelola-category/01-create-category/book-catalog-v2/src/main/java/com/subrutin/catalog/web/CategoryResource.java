package com.subrutin.catalog.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.CategoryCreateRequestDTO;
import com.subrutin.catalog.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
public class CategoryResource {
	
	private final CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<Void> createCategory(@RequestBody @Valid CategoryCreateRequestDTO dto){
		categoryService.createCategory(dto);
		return ResponseEntity.created(URI.create("/v1/categories")).build();
	}

}
