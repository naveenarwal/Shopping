package com.shs.app.entity;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private GroupVarient productVariant;

    @ManyToOne
    @JoinColumn(name="order_id")
     private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    
    
	public OrderItem(BigDecimal price, GroupVarient productVariant, Order order, Product product) {
		super();
		this.price = price;
		this.productVariant = productVariant;
		this.order = order;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public GroupVarient getProductVariant() {
		return productVariant;
	}

	public void setProductVariant(GroupVarient productVariant) {
		this.productVariant = productVariant;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

    
    
  }

