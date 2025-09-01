package com.subrutin.catalog.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.dto.CategoryCreateRequestDTO;
import com.subrutin.catalog.dto.CategoryDetailResponseDTO;
import com.subrutin.catalog.dto.CategoryListResponseDTO;
import com.subrutin.catalog.dto.CategoryUpdateRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;
import com.subrutin.catalog.dto.query.CategoryQueryDTO;
import com.subrutin.catalog.exception.ResourceNotFoundException;
import com.subrutin.catalog.repository.CategoryRepository;
import com.subrutin.catalog.service.CategoryService;

import jakarta.transaction.Transactional;
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

	@Override
	public ResultPageResponseDTO<List<CategoryListResponseDTO>> findCategoryList(Integer pages, Integer limit, String sortBy,
			String direction, String categoryName) {
		categoryName = StringUtils.isBlank(categoryName)? "%" :categoryName+"%";
		Sort sort = Sort.by(new Sort.Order(Direction.valueOf(direction.toUpperCase()), sortBy));
		Pageable pageable =PageRequest.of(pages, limit, sort);
		Page<Category> pageResult =  categoryRepository.findAllByNameLikeIgnoreCase(categoryName, pageable);
		List<CategoryListResponseDTO> dtos= pageResult.getContent().stream().map((c)->{
			return new CategoryListResponseDTO(c.getCode(), c.getName());
		}).collect(Collectors.toList());
		
		return new ResultPageResponseDTO(dtos, pageResult.getTotalPages(), pageResult.getTotalElements());
	}

	@Override
	public CategoryDetailResponseDTO findCategoryDetail(String code) {
		Category category =  categoryRepository.findByCodeIgnoreCase(code)
			.orElseThrow(()-> new ResourceNotFoundException("category code not found"));
		return new CategoryDetailResponseDTO(category.getCode(), 
				category.getName(), category.getDescription());
	}

	@Override
	public void updateCategory(String code, CategoryUpdateRequestDTO dto) {
		Category category =  categoryRepository.findByCodeIgnoreCase(code)
				.orElseThrow(()-> new ResourceNotFoundException("category code not found"));
		category.setName(dto.name());
		category.setDescription(dto.description());
		
		categoryRepository.save(category);		
	}

	@Transactional
	@Override
	public void deleteCategory(String code) {
		Category category =  categoryRepository.findByCodeIgnoreCase(code)
				.orElseThrow(()-> new ResourceNotFoundException("category code not found"));	
		
		category.setDeleted(true);
		categoryRepository.save(category);
	}

	@Override
	public Set<Category> findCategories(List<String> categoryCodeList) {
		Set<Category> categories =  categoryRepository.findByCodeInIgnoreCase(categoryCodeList);
		if(categories.isEmpty()||(categories.size()!=categoryCodeList.size())) {
			throw new ResourceNotFoundException("category code not found");
		}
		return categories;
	}

	@Override
	public Map<Long, Set<CategoryListResponseDTO>> findCategoriesMap(Set<Long> bookIdSet) {
		Set<CategoryQueryDTO> categoryDTOSet = categoryRepository.findCategoryByBookIdSet(bookIdSet);
		Map<Long, Set<CategoryListResponseDTO>> categoryMap = new HashMap<>();
		Set<CategoryListResponseDTO> c = null;
		for(CategoryQueryDTO q:categoryDTOSet) {
			if(!categoryMap.containsKey(q.bookId())) {
				c = new HashSet<>();
			}else {
				 c = categoryMap.get(q.bookId());
			}
			c.add(new CategoryListResponseDTO(q.categoryCode(), q.categoryName()));
			categoryMap.put(q.bookId(), c);
		}
		
		
		return categoryMap;
	}

}
