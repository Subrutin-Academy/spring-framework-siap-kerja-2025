package com.subrutin.catalog.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.subrutin.catalog.domain.Category;
import com.subrutin.catalog.dto.query.CategoryQueryDTO;

public interface CategoryRepository extends JpaRepository<Category, String> {
	
	public Page<Category> findAllByNameLikeIgnoreCase(String categoryName, Pageable pageable);
	
	public Optional<Category> findByCodeIgnoreCase(String code);
	
	public Set<Category> findByCodeInIgnoreCase(List<String> codes);
	
	@Query("""
			SELECT new com.subrutin.catalog.dto.query.CategoryQueryDTO(
			b.id, bc.code, bc.name
			) 
			FROM Book b
			JOIN b.categories bc 
			WHERE b.id IN :bookIdSet
			""")
	public Set<CategoryQueryDTO> findCategoryByBookIdSet(Set<Long> bookIdSet);

}
