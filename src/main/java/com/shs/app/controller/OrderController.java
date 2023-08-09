package com.shs.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shs.app.entity.Order;
import com.shs.app.entity.OrderItem;
import com.shs.app.exceptions.DataNotFoundException;
import com.shs.app.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderService oservice;

	@PostMapping
	public ResponseEntity<Order> saveOrder(@RequestBody Order o) {

		ResponseEntity<Order> rs = new ResponseEntity<Order>(oservice.addOrder(o), HttpStatus.CREATED);
		return rs;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<Order>> getAllOrdersbyUserID(@PathVariable Long userId) {

		ResponseEntity<List<Order>> rs = new ResponseEntity<List<Order>>(oservice.getAllOrderByUserId(userId),
				HttpStatus.OK);
		return rs;
	}

	@GetMapping("/getorder/{orderId}")
	public ResponseEntity<Order> getAllOrders(@PathVariable Long orderId) {

		ResponseEntity<Order> rs = new ResponseEntity<Order>(oservice.getOrder(orderId), HttpStatus.OK);
		return rs;
	}

	@PostMapping("/addOrderItem")
	public ResponseEntity<Order> addOrdemerIt(@RequestBody OrderItem oi) {

		ResponseEntity<Order> rs = null;
		try {
			rs = new ResponseEntity<Order>(oservice.addOrderItem(oi), HttpStatus.CREATED);
		} catch (DataNotFoundException e) {
			// TODO: handle exception
			throw new DataNotFoundException("Order Not found with order  =>" + oi.getOrder());

		}
		return rs;
	}

}
