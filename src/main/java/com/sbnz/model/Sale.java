package com.sbnz.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 
 * @author Predrag Falcic
 * 
 * Akcijski dogadjaj
 * Create table for Sale in database
 *
 */
@Entity
public class Sale {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String code;
	
	private String name;
	
	private String activePeriod; // Period za koj je akcijski dogadjaj aktivan, 27.06-27.07
	
	private int Discount; // Procentualni popust
	
	@OneToMany(mappedBy="sale")
	private Collection<ArticalCategory> articalCategoriesOnSale; // Lista kategorija artikala koje su na akciji
	
	@ManyToMany(mappedBy = "sales")
	private Collection<Bill> bills;
	
	@ManyToOne
	private BillItem billItem;
	
	public Sale(){}
	
	public Sale(String code, String name, String activePeriod, int discount,
			Collection<ArticalCategory> articalCategoriesOnSale, Collection<Bill> bills, BillItem billItem) {
		super();
		this.code = code;
		this.name = name;
		this.activePeriod = activePeriod;
		Discount = discount;
		this.articalCategoriesOnSale = articalCategoriesOnSale;
		this.bills = bills;
		this.billItem = billItem;
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

	public String getActivePeriod() {
		return activePeriod;
	}

	public void setActivePeriod(String activePeriod) {
		this.activePeriod = activePeriod;
	}

	public int getDiscount() {
		return Discount;
	}

	public void setDiscount(int discount) {
		Discount = discount;
	}

	public Collection<ArticalCategory> getArticalCategoriesOnSale() {
		return articalCategoriesOnSale;
	}

	public void setArticalCategoriesOnSale(Collection<ArticalCategory> articalCategoriesOnSale) {
		this.articalCategoriesOnSale = articalCategoriesOnSale;
	}

	public Collection<Bill> getBills() {
		return bills;
	}

	public void setBills(Collection<Bill> bills) {
		this.bills = bills;
	}

	public BillItem getBillItem() {
		return billItem;
	}

	public void setBillItem(BillItem billItem) {
		this.billItem = billItem;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", code=" + code + ", name=" + name + ", activePeriod=" + activePeriod + ", Discount="
				+ Discount + ", articalCategoriesOnSale=" + articalCategoriesOnSale + ", bills=" + bills + ", billItem="
				+ billItem + "]";
	}
}
