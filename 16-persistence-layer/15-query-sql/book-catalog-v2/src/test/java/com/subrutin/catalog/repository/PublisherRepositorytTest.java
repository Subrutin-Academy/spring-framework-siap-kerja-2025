package com.subrutin.catalog.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.subrutin.catalog.domain.Publisher;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PublisherRepositorytTest {
	
	@Autowired
	private PublisherRepository publisherRepository;

	@Test
	void testFindByName_Success() {
		Publisher publisher = new Publisher();
		publisher.setName("Apress");
		publisher.setDescription("Apress, didirikan pada tahun 1999 di New York dan sekarang bagian dari Springer Nature");
		publisherRepository.save(publisher);
		
		List<Publisher> publishers = publisherRepository.findAllByName("Apress");
		assertEquals("Apress", publishers.getFirst().getName());
	}
	
	
	@Test
	void testFindByName_Incomplete() {
		Publisher publisher = new Publisher();
		publisher.setName("Apress");
		publisher.setDescription("Apress, didirikan pada tahun 1999 di New York dan sekarang bagian dari Springer Nature");
		publisherRepository.save(publisher);
		
		List<Publisher> publishers = publisherRepository.findAllByName("Ap");
		assertEquals(0, publishers.size());
	}
	
	
	@Test
	void testFindByNameLikeIgnoreCase_Incomplete() {
		Publisher publisher = new Publisher();
		publisher.setName("Apress");
		publisher.setDescription("Apress, didirikan pada tahun 1999 di New York dan sekarang bagian dari Springer Nature");
		publisherRepository.save(publisher);
		
		List<Publisher> publishers = publisherRepository.findAllByNameLikeIgnoreCase("ap%");
		assertEquals(1, publishers.size());
	}
	
	@Test
	void testFindByPublisherNameUsingJPQL_Incomplete() {
		Publisher publisher = new Publisher();
		publisher.setName("Apress");
		publisher.setDescription("Apress, didirikan pada tahun 1999 di New York dan sekarang bagian dari Springer Nature");
		publisherRepository.save(publisher);
		
		List<Publisher> publishers = publisherRepository.findAllByPublisherNameUsingJPQL("ap%");
		assertEquals(1, publishers.size());
	}
	
	@Test
	void testFindByPublisherNameUsingSQL_Incomplete() {
		Publisher publisher = new Publisher();
		publisher.setName("Apress");
		publisher.setDescription("Apress, didirikan pada tahun 1999 di New York dan sekarang bagian dari Springer Nature");
		publisherRepository.save(publisher);
		
		List<Publisher> publishers = publisherRepository.findAllByPublisherNameUsingSQL("ap%");
		assertEquals(1, publishers.size());
	}
	

}
