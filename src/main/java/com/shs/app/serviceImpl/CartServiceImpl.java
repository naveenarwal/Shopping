package com.shs.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shs.app.entity.Cart;
import com.shs.app.entity.CartItem;
import com.shs.app.entity.Order;
import com.shs.app.entity.OrderItem;
import com.shs.app.entity.User;
import com.shs.app.exceptions.AlreadyFoundDataException;
import com.shs.app.exceptions.DataNotFoundException;
import com.shs.app.repo.ICartItemRepository;
import com.shs.app.repo.ICartRepository;
import com.shs.app.repo.IOrderItemRepository;
import com.shs.app.repo.IOrderRepository;
import com.shs.app.service.ICartService;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	private ICartRepository cartRepository;

	@Autowired
	private ICartItemRepository cartItemRepository;

	@Autowired
	private IOrderRepository orderRepository;
	@Autowired
	private IOrderItemRepository orderItemRepository;

	@Override
	public Cart addCart(Long userID) {
		Cart cart = cartRepository.getCartByUserId(userID);
		if (cart != null) {
			throw new AlreadyFoundDataException(" Cart with this user already present");
		}
		Cart c = new Cart();
		User u = new User(userID);
		c.setUser(u);
		return cartRepository.save(c);
	}

	@Override
	public List<CartItem> getCartItems(Long cartId) {

		return cartItemRepository.getCartItemByCartId(cartId);
	}

	@Override
	public CartItem addCartItemToCartbyId(CartItem cartItem) {
		CartItem cartItem2 = cartItemRepository.findByCartAndProductId(cartItem.getCart().getId(),
				cartItem.getProductId().getId());
		if (cartItem2 == null) {
			int quantity = 0;
			cartItem.setQuantity(quantity++);
			return cartItemRepository.save(cartItem);
		} else if (cartItem2 != null) {
			int quantity = cartItem2.getQuantity();
			cartItem2.setQuantity(++quantity);
			return cartItemRepository.save(cartItem2);

		} else {
			throw new AlreadyFoundDataException(" Cart Not  present");
		}

	}

	@Override
	public Cart removeCartItemByCartIdAndProductId(Long cartId, Long prooductId) {
		CartItem cartItem = cartItemRepository.findByCartAndProductId(cartId, prooductId);
		if (cartItem != null) {
			cartItemRepository.deleteById(cartItem.getId());
			return cartRepository.findById(cartId).get();
		} else {
			throw new DataNotFoundException(" Cart Not  present with cartId =>  " + cartId);
		}
	}

	@Override
	public CartItem addCartItemQuantity(Integer quantity, Long cartItemId) {
		CartItem cartItem = cartItemRepository.getCartItemByCartItemId(cartItemId);
		if (cartItem != null) {
			// int quantity2 = cartItem.getQuantity();
			cartItem.setQuantity(quantity);
			return cartItemRepository.save(cartItem);
		}
		throw new DataNotFoundException("No Suct CartItem Exist");
	}

	@Override
	public Order createOrderFromCart(Long cartItemId, Order o) {
		// Order order = orderRepository.findById(o.getId()).get();
		Order order2 = new Order();
		CartItem cartItem = cartItemRepository.findById(cartItemId).get();
		order2 = orderRepository.save(o);
		OrderItem orderItem = new OrderItem(cartItem.getPrice(), cartItem.getProductId().getGv(), order2,
				cartItem.getProductId());
		orderItemRepository.save(orderItem);
		// order2.getOrderItem().add(orderItem);
		cartItemRepository.deleteById(cartItem.getId());
		return orderRepository.findById(order2.getId()).get();
	}

}
