package com.shs.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shs.app.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {

	Product findByProductName(String productName);
}
