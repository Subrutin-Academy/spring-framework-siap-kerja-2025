package com.subrutin.catalog.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.subrutin.catalog.domain.Author;
import com.subrutin.catalog.dto.query.AuthorQueryDTO;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	public List<Author> findAllByNameLikeIgnoreCase(String name);
	
	public Set<Author> findAllByIdIn(List<Long> authorsId);
	
	@Query("""
			SELECT new com.subrutin.catalog.dto.query.AuthorQueryDTO(
			b.id, ba.id, ba.name)
			FROM Book b 
			JOIN b.authors ba 
			WHERE b.id IN :bookIdSet
			""")
	public Set<AuthorQueryDTO> findAuthorsByBookIdSet(Set<Long> bookIdSet);
}
