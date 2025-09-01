package com.subrutin.catalog.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
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
	
	@Test
	void testUpdatePublisher_success() {
		Publisher publisher = publisherRepository.findById(1L)
			.orElseThrow(()-> new RuntimeException("publisher id not found"));
		publisher.setDescription("modified description");
		System.out.println("start publisher update");
		publisherRepository.saveAndFlush(publisher);
		System.out.println("finish publisher update");
		
		System.out.println("start find publisher after update");
		Publisher publisherUpdated = publisherRepository.findById(1L)
				.orElseThrow(()-> new RuntimeException("publisher id not found"));
		System.out.println("finish find publisher after update");
		assertEquals("modified description", publisherUpdated.getDescription());
		
	}
	
	@Test
	void TestUpdatePublisherModifying_success() {
		System.out.println("start publisher update using @Modifying");
		publisherRepository.updatePublisherDescription("modified description", 1L);
		System.out.println("finish publisher update using @Modifying");

		System.out.println("start find publisher after update using @Modifying");
		Publisher publisherUpdated = publisherRepository.findById(1L)
				.orElseThrow(()-> new RuntimeException("publisher id not found"));
		System.out.println("finish find publisher after update using @Modifying");
		assertEquals("modified description", publisherUpdated.getDescription());
	}

}







