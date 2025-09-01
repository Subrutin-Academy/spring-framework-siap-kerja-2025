package com.subrutin.catalog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.subrutin.catalog.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	
	public Page<Category> findAllByNameLikeIgnoreCase(String categoryName, Pageable pageable);

}
