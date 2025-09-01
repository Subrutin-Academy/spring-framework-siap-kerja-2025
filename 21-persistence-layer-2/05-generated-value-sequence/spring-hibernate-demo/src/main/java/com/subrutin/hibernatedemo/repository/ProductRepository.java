package com.subrutin.hibernatedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.subrutin.hibernatedemo.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
