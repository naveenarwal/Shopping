package com.shs.app.service;

import java.util.List;

import com.shs.app.entity.Order;
import com.shs.app.entity.OrderItem;

public interface IOrderService {
	Order addOrder(Order order);
	Order getOrder(Long orderId);
	Order updateOrder(Order order);
	List<Order> getAllOrderByUserId(Long userId);
	Order addOrderItem(OrderItem item);

}
