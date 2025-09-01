package com.subrutin.catalog.web;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.CategoryCreateRequestDTO;
import com.subrutin.catalog.dto.CategoryDetailResponseDTO;
import com.subrutin.catalog.dto.CategoryListResponseDTO;
import com.subrutin.catalog.dto.CategoryUpdateRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;
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
	
	@GetMapping
	public ResponseEntity<ResultPageResponseDTO<List<CategoryListResponseDTO>>> findCategoryList(
			@RequestParam(defaultValue = "0") Integer pages,
			@RequestParam(defaultValue = "10") Integer limit,
			@RequestParam(defaultValue = "code") String sortBy,
			@RequestParam(defaultValue = "asc") String direction,
			@RequestParam(required = false) String categoryName){
		return ResponseEntity.ok(categoryService.findCategoryList(pages, limit, sortBy, direction, categoryName));
	}
	
	@GetMapping("{code}")
	public ResponseEntity<CategoryDetailResponseDTO> findCategoryDetail(@PathVariable String code){
		return ResponseEntity.ok(categoryService.findCategoryDetail(code));
	}
	
	@PutMapping("{code}")
	public ResponseEntity<Void> updateCategory(@PathVariable String code, @RequestBody @Valid CategoryUpdateRequestDTO dto){
		categoryService.updateCategory(code, dto);
		return ResponseEntity.ok().build();
	}

}
