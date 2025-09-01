package com.subrutin.catalog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.subrutin.catalog.domain.Publisher;

import jakarta.transaction.Transactional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	//SELECT * FROM publisher p WHERE p.name='Airlangga'
	public List<Publisher> findAllByName(String publisherName);
	
	//SELECT * FROM publisher p WHERE UPPER(p.name) LIKE UPPER('Airlangga')
	public List<Publisher> findAllByNameLikeIgnoreCase(String publisherName);
	
	@Query("SELECT p FROM Publisher p WHERE UPPER(p.name) LIKE UPPER(:publisherName)")
	public List<Publisher> findAllByPublisherNameUsingJPQL(String publisherName);
	
	@Query(value="SELECT p.id, p.name, p.description FROM publisher p WHERE UPPER(p.name) LIKE UPPER(:publisherName)",
			nativeQuery = true)
	public List<Publisher> findAllByPublisherNameUsingSQL(String publisherName);
	
	@Modifying
	@Query("UPDATE Publisher p SET p.description=:description WHERE p.id=:id")
	public void updatePublisherDescription(String description, Long id);
	
	//SELECT * FROM publisher where UPPER(name) LIKE UPPER(:publisherName)
	public Page<Publisher> findByNameLikeIgnoreCase(String publisherName, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("UPDATE Publisher p SET p.deleted=true WHERE p.id=:id")
	public void softDeletePublisher(Long id);


}
