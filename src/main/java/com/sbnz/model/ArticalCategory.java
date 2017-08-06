package com.sbnz.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 
 * @author Predrag Falcic
 * 
 * Create ArticalCategory table in database
 *
 */
@Entity
public class ArticalCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String code;
	
	@Column(unique=true)
	private String name;
	
	@OneToMany(mappedBy="articalCategory")
	private Collection<SubCategory> subCategories; 
	
	private int maxDiscount;
	
	@OneToMany(mappedBy="category")
	private Collection<Artical> articals;
	
	@ManyToOne
	private Sale sale;
	
	public ArticalCategory(){}
	
	public ArticalCategory(String code, String name, Collection<SubCategory> subCategories, int maxDiscount,
			Collection<Artical> articals, Sale sale) {
		super();
		this.code = code;
		this.name = name;
		this.subCategories = subCategories;
		this.maxDiscount = maxDiscount;
		this.articals = articals;
		this.sale = sale;
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

	public Collection<SubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Collection<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public int getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(int maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public Collection<Artical> getArticals() {
		return articals;
	}

	public void setArticals(Collection<Artical> articals) {
		this.articals = articals;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	@Override
	public String toString() {
		return "ArticalCategory [id=" + id + ", code=" + code + ", name=" + name + ", subCategories=" + subCategories
				+ ", maxDiscount=" + maxDiscount + ", articals=" + articals + ", sale=" + sale + "]";
	}
	
}











