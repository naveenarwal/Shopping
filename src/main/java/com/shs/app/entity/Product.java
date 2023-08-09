package com.shs.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String productName;
	private String price;
	private String description;
	@CreatedDate
	private String created;

	private Long quantity;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="prodt_id")
	@JsonIgnoreProperties("product")
	private List<Image> productImages = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "group_id")
//    private ProductGroup group;

	@ManyToOne
	@JoinColumn(name = "groupv_id")
	private GroupVarient gv;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", price=" + price + ", description="
				+ description + ", created=" + created + ", gv=" + gv + ", user=" + user + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public List<Image> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<Image> productImages) {
		this.productImages = productImages;
	}

	public GroupVarient getGv() {
		return gv;
	}

	public void setGv(GroupVarient gv) {
		this.gv = gv;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
