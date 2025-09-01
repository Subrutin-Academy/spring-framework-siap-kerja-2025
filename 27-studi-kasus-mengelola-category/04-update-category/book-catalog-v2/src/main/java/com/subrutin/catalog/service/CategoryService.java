package com.subrutin.catalog.service;

import java.util.List;

import com.subrutin.catalog.dto.CategoryCreateRequestDTO;
import com.subrutin.catalog.dto.CategoryDetailResponseDTO;
import com.subrutin.catalog.dto.CategoryListResponseDTO;
import com.subrutin.catalog.dto.CategoryUpdateRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;

public interface CategoryService {
	
	public void createCategory(CategoryCreateRequestDTO dto);
	
	public ResultPageResponseDTO<List<CategoryListResponseDTO>> findCategoryList(
			Integer pages,
			Integer limit,
			String sortBy,
			String direction,
			String categoryName
			);
	
	public CategoryDetailResponseDTO findCategoryDetail(String code);
	
	public void updateCategory(String code, CategoryUpdateRequestDTO dto);

}
