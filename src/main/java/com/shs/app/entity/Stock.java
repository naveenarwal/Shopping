package com.shs.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

	@Id
	@GeneratedValue
	private Long stockId;
	
	private Long quantity;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	private Product product;
}
