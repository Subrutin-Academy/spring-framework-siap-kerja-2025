package com.subrutin.hibernatedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subrutin.hibernatedemo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
