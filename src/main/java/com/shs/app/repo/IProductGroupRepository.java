package com.shs.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shs.app.entity.ProductGroup;

public interface IProductGroupRepository extends JpaRepository<ProductGroup, Long>{

	
	ProductGroup findByGroupName(String groupName);
}
