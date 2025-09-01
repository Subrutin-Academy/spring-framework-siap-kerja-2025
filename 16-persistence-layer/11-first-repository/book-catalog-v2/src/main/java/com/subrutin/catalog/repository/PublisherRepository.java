package com.subrutin.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subrutin.catalog.domain.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	//SELECT * FROM publisher p WHERE p.name='Airlangga'
	public List<Publisher> findAllByName(String publisherName);
}
