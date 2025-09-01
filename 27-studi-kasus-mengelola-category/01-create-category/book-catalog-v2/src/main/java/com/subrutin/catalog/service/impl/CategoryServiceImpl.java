package com.subrutin.catalog.service.impl;

import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.dto.CategoryCreateRequestDTO;
import com.subrutin.catalog.repository.CategoryRepository;
import com.subrutin.catalog.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;

	@Override
	public void createCategory(CategoryCreateRequestDTO dto) {
		Category category = new Category();
		category.setCode(dto.code());
		category.setName(dto.name());
		category.setDescription(dto.description());
		
		categoryRepository.save(category);
		
	}

}
