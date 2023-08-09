package com.shs.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shs.app.entity.Cart;
import com.shs.app.entity.CartItem;
import com.shs.app.entity.Order;
import com.shs.app.exceptions.DataNotFoundException;
import com.shs.app.service.ICartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ICartService cartService;
	
	
	@PostMapping("/")
	public ResponseEntity<Cart> createCart(@RequestParam("userId") Long userId){
		return new ResponseEntity<Cart>(
				cartService.addCart(userId)
				,HttpStatus.CREATED);
	}
	
	@PostMapping("/addItem")
	public ResponseEntity<CartItem> addcartItem(@RequestBody CartItem cartItem){
		return new ResponseEntity<CartItem>(
				cartService.addCartItemToCartbyId(cartItem)
				,HttpStatus.CREATED);
	}
	
	@GetMapping("/{cartId}")
	public ResponseEntity<List<CartItem>> addcartItem(@PathVariable long cartId){
		return new ResponseEntity<List<CartItem>>(
				cartService.getCartItems(cartId)
				,HttpStatus.OK);
	}
	
	@PutMapping("/{cartItemId}/{quantity}")
	public ResponseEntity<?> setProductQuantityInCart(@PathVariable Long cartItemId, @PathVariable Integer quantity){
		return ResponseEntity.ok(this.cartService.addCartItemQuantity(quantity, cartItemId));
	}
	
	@DeleteMapping("/{productId}/{cartId}")
	public ResponseEntity<?> removeProductFromcart(@PathVariable Long productId,@PathVariable Long cartId){
		return ResponseEntity.ok(this.cartService.removeCartItemByCartIdAndProductId(cartId, productId));
	}
	
	@PutMapping("/orderFromCart/{cartItemId}")
	public ResponseEntity<?> orderFromCart(@RequestBody Order order,@PathVariable Long cartItemId){
		System.out.println(cartItemId);
		ResponseEntity<Order> rs = null;
		try {
			rs = new  ResponseEntity<Order>(this.cartService.createOrderFromCart(cartItemId, order) ,HttpStatus.CREATED);
		} catch (DataNotFoundException e) {
			// TODO: handle exception
			throw  new DataNotFoundException("CartItem Not found");
		}
		return rs;
	}
	
}
