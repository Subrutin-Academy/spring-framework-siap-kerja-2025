package com.subrutin.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.subrutin.catalog.domain.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	//SELECT * FROM publisher p WHERE p.name='Airlangga'
	public List<Publisher> findAllByName(String publisherName);
	
	//SELECT * FROM publisher p WHERE UPPER(p.name) LIKE UPPER('Airlangga')
	public List<Publisher> findAllByNameLikeIgnoreCase(String publisherName);
	
	@Query("SELECT p FROM Publisher p WHERE UPPER(p.name) LIKE UPPER(:publisherName)")
	public List<Publisher> findAllByPublisherNameUsingJPQL(String publisherName);


}
