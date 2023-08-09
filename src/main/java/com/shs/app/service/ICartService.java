package com.shs.app.service;

import java.util.List;

import com.shs.app.entity.Cart;
import com.shs.app.entity.CartItem;
import com.shs.app.entity.Order;

public interface ICartService {

	public Cart addCart(Long userId);

	public List<CartItem> getCartItems(Long cartId);

	public CartItem addCartItemToCartbyId(CartItem cartItem);

	public Cart removeCartItemByCartIdAndProductId(Long cartId, Long prooductId);

	public CartItem addCartItemQuantity(Integer quantity,Long cartItemId );
	
	public Order createOrderFromCart(Long cartItemId,Order o);
}
