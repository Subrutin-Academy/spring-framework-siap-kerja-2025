package com.subrutin.catalog.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.subrutin.catalog.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	
	public Page<Category> findAllByNameLikeIgnoreCase(String categoryName, Pageable pageable);
	
	public Optional<Category> findByCodeIgnoreCase(String code);
	
	public Set<Category> findByCodeInIgnoreCase(List<String> codes);

}
