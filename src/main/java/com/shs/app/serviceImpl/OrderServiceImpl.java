package com.shs.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shs.app.entity.Order;
import com.shs.app.entity.OrderItem;
import com.shs.app.exceptions.DataNotFoundException;
import com.shs.app.repo.IOrderRepository;
import com.shs.app.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService{

		@Autowired
		private IOrderRepository orepo;
		
		@Override
		public Order addOrder(Order order) {
			return orepo.save(order);
		}

		@Override
		public Order getOrder(Long orderId) {
			// TODO Auto-generated method stub
			return orepo.findById(orderId).get();
		}

		@Override
		public List<Order> getAllOrderByUserId(Long userId) {
			// TODO Auto-generated method stub
			return orepo.getOrderByUserId(userId);
		}

		@Override
		public Order updateOrder(Order order) {
			// TODO Auto-generated method stub
		
			Order o = orepo.findById(order.getId()).get();
			if(o!=null) {

				order.getOrderItem().addAll(o.getOrderItem());
				return orepo.save(order);
			}
			else {
				throw new DataNotFoundException("Order Not found !!");
			}
			
		}

		@Override
		public Order addOrderItem(OrderItem item) {
		
			Order order = orepo.findById(item.getOrder().getId()).get();
			if(order!=null) {
				order.getOrderItem().add(item);
				return orepo.save(order);
			}
			else {
				throw new DataNotFoundException("Order Not found with order  =>" +item.getOrder());
			}
			
		}

	}


