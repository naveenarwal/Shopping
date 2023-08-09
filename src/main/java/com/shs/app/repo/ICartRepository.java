package com.shs.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shs.app.entity.Cart;

public interface ICartRepository extends JpaRepository<Cart, Long> {

	@Query("select c from Cart c where c.user.id =?1 ")
	Cart getCartByUserId(Long userId);
	
	
	
}
