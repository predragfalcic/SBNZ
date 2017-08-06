package com.sbnz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 
 * @author Predrag Falcic
 *
 * Create Artical table in database
 */
@Entity
public class Artical {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String code;
	
	private String name;
	
	@ManyToOne
	private ArticalCategory category;
	
	@ManyToOne
	private SubCategory subCategory;
	
	private Double prize;
	
	private int inStock;
	
	private String state; // Aktivan ili arhiviran
	
	@OneToOne(mappedBy="artical")
	private BillItem billItem;
	
	public Artical(){}

	public Artical(String code, String name, ArticalCategory category, SubCategory subCategory, Double prize,
			int inStock, String state, BillItem billItem) {
		super();
		this.code = code;
		this.name = name;
		this.category = category;
		this.subCategory = subCategory;
		this.prize = prize;
		this.inStock = inStock;
		this.state = state;
		this.billItem = billItem;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArticalCategory getCategory() {
		return category;
	}

	public void setCategory(ArticalCategory category) {
		this.category = category;
	}

	public Double getPrize() {
		return prize;
	}

	public void setPrize(Double prize) {
		this.prize = prize;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public BillItem getBillItem() {
		return billItem;
	}

	public void setBillItem(BillItem billItem) {
		this.billItem = billItem;
	}

	@Override
	public String toString() {
		return "Artical [id=" + id + ", code=" + code + ", name=" + name + ", category=" + category + ", subCategory="
				+ subCategory + ", prize=" + prize + ", inStock=" + inStock + ", state=" + state + ", billItem="
				+ billItem + "]";
	}
	
}
