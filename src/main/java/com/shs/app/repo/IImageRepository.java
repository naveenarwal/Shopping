package com.shs.app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shs.app.entity.Image;
import com.shs.app.entity.Product;

public interface IImageRepository extends JpaRepository<Image, Long> {

	@Query("select i from Image i where i.product.id=?1")
	List<Image> findByProduct(Long pid);
	
	//@Query("select i from Image i where i.name=?1")
	 Optional<Image> findByName(String name);
}
