package com.sbnz.model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 
 * @author Predrag Falcic
 * 
 * Create Bill table in the database
 *  
 */
@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String code;
	
	private Timestamp dateTime;
	
	@ManyToOne
	private User buyer; // Mozda tu treba staviti profil korisnika
	
	private String state; // poruceno, otkazano ili uspesno realizovano
	
	private Double originalPrize;
	
	private int discount;
	
	private Double finalPrize;
	
	private int numSpentRewardingPoints; // Broj potrosenih nagradnih bodova
	
	private int numAchievedRewardingPoints; // broh ostvarenih nagradnih bodova
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bill_sales", 
    joinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "sale_id", referencedColumnName = "id"))
	private Set<Sale> sales;
	
	@OneToMany
	private Collection<BillItem> billItems;
	
	@ManyToOne
	private BillDiscount billDiscount;
	
	@ManyToOne
	private BillItemDiscount billItemDiscount;
	
	public Bill(){}
	
	public Bill(String code, Timestamp dateTime, User buyer, String state, Double originalPrize, int discount,
			Double finalPrize, int numSpentRewardingPoints, int numAchievedRewardingPoints, Set<Sale> sales,
			Collection<BillItem> billItems, BillDiscount billDiscount, BillItemDiscount billItemDiscount) {
		super();
		this.code = code;
		this.dateTime = dateTime;
		this.buyer = buyer;
		this.state = state;
		this.originalPrize = originalPrize;
		this.discount = discount;
		this.finalPrize = finalPrize;
		this.numSpentRewardingPoints = numSpentRewardingPoints;
		this.numAchievedRewardingPoints = numAchievedRewardingPoints;
		this.sales = sales;
		this.billItems = billItems;
		this.billDiscount = billDiscount;
		this.billItemDiscount = billItemDiscount;
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

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getOriginalPrize() {
		return originalPrize;
	}

	public void setOriginalPrize(Double originalPrize) {
		this.originalPrize = originalPrize;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Double getFinalPrize() {
		return finalPrize;
	}

	public void setFinalPrize(Double finalPrize) {
		this.finalPrize = finalPrize;
	}

	public int getNumSpentRewardingPoints() {
		return numSpentRewardingPoints;
	}

	public void setNumSpentRewardingPoints(int numSpentRewardingPoints) {
		this.numSpentRewardingPoints = numSpentRewardingPoints;
	}

	public int getNumAchievedRewardingPoints() {
		return numAchievedRewardingPoints;
	}

	public void setNumAchievedRewardingPoints(int numAchievedRewardingPoints) {
		this.numAchievedRewardingPoints = numAchievedRewardingPoints;
	}

	public Set<Sale> getSales() {
		return sales;
	}

	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}

	public Collection<BillItem> getBillItems() {
		return billItems;
	}

	public void setBillItems(Collection<BillItem> billItems) {
		this.billItems = billItems;
	}

	public BillDiscount getBillDiscount() {
		return billDiscount;
	}

	public void setBillDiscount(BillDiscount billDiscount) {
		this.billDiscount = billDiscount;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", code=" + code + ", dateTime=" + dateTime + ", buyer=" + buyer + ", state=" + state
				+ ", originalPrize=" + originalPrize + ", discount=" + discount + ", finalPrize=" + finalPrize
				+ ", numSpentRewardingPoints=" + numSpentRewardingPoints + ", numAchievedRewardingPoints="
				+ numAchievedRewardingPoints + ", sales=" + sales + ", billItems=" + billItems + ", billDiscount="
				+ billDiscount + ", billItemDiscount=" + billItemDiscount + "]";
	}
}
