package com.shs.app.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group_variants")
public class GroupVarient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "variant_name")
	private String variantName;

	  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	  @JoinColumn(name="groupId")
	    private ProductGroup group;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVariantName() {
		return variantName;
	}

	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}

	public ProductGroup getGroup() {
		return group;
	}

	public void setGroup(ProductGroup group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "GroupVarient [id=" + id + ", variantName=" + variantName + ", group=" + group + "]";
	}
	  

}
