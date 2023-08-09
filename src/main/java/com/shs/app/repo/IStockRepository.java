package com.shs.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shs.app.entity.Product;
import com.shs.app.entity.Stock;

public interface IStockRepository extends JpaRepository<Stock, Long>{

	Stock findByProduct(Product product);
}
