package com.subrutin.hibernatedemo.repository;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.subrutin.hibernatedemo.domain.Product;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@SpringBootTest
public class ProductRepositoryTest {
	
	@Inject
	ProductRepository productRepository;
	
	@Test
	public void testSave() {
		Product product1 = new Product();
		product1.setName("Lenovo Legion Slim 5");
		product1.setPrice(BigDecimal.valueOf(20_000_000));
		
		productRepository.save(product1);
		
		Product product2 = new Product();
		product2.setName("Macbook Air M1");
		product2.setPrice(BigDecimal.valueOf(20_000_000));
		productRepository.save(product2);
		
		Product product3 = new Product();
		product3.setName("Macbook Air M2");
		product3.setPrice(BigDecimal.valueOf(20_000_000));
		productRepository.save(product3);

		
	}

}
