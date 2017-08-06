package com.sbnz.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * @author Predrag Falcic
 * 
 * Bill item discount
 * 
 * Cretes BillItemDiscount table in the database
 *
 */
@Entity
public class BillItemDiscount {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String code;
	
	@OneToMany(mappedBy="billDiscount")
	private Collection<Bill> bills; // Racuni na koje se odnosi ovaj popust
	
	@OneToMany(mappedBy="billItemDiscount")
	private Collection<BillItem> billItems; // Stavke racuna na koje se odnosi ovaj popust
	
	private Double discountPercent; // Procenat umanjenja
	
	private String discountType; // Oznaka da li je popust osnovni ili dodatni
	
	public BillItemDiscount(){}

	public BillItemDiscount(String code, Collection<Bill> bills, Collection<BillItem> billItems, Double discountPercent,
			String discountType) {
		super();
		this.code = code;
		this.bills = bills;
		this.billItems = billItems;
		this.discountPercent = discountPercent;
		this.discountType = discountType;
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

	public Collection<Bill> getBills() {
		return bills;
	}

	public void setBills(Collection<Bill> bills) {
		this.bills = bills;
	}

	public Collection<BillItem> getBillItems() {
		return billItems;
	}

	public void setBillItems(Collection<BillItem> billItems) {
		this.billItems = billItems;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	@Override
	public String toString() {
		return "BillItemDiscount [id=" + id + ", code=" + code + ", bills=" + bills + ", billItems=" + billItems
				+ ", discountPercent=" + discountPercent + ", discountType=" + discountType + "]";
	}
}
