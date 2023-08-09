package com.shs.app.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.shs.app.entity.Cart;
import com.shs.app.entity.CartItem;
import com.shs.app.entity.Product;

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {

	CartItem findByProductId(Long productId);

	@Query("select ci from CartItem ci where ci.cart.id=?1 ")
	List<CartItem> getCartItemByCartId(Long cartId);

	@Query("select ci from CartItem ci where ci.id=?1 ")
	CartItem getCartItemByCartItemId(Long cartItemId);

	@Query("select ci from CartItem ci where ci.cart.id=?1 and  ci.productId.id=?2")
	public CartItem findByCartAndProductId(Long cartId, Long productId);

	//@Transactional
	//@Modifying
	//@Query("select ci from CartItem ci where ci.cart.id=?1 and ci.productId.id=?2")
	//public CartItem deleteByCartIdAndProductId(Long cartId, Long productId);

}
