package com.shs.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shs.app.entity.Order;

public interface IOrderRepository extends JpaRepository<Order, Long>{
	@Query("select o from Order o where o.user.id =?1 ")
	List<Order> getOrderByUserId(Long userId);
	
}
