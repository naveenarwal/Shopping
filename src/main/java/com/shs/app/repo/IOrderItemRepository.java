package com.shs.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shs.app.entity.OrderItem;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Long>{

}
