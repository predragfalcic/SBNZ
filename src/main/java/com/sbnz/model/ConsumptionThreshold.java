package com.sbnz.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * 
 * @author Predrag Falcic
 * 
 * Create Consumption threshold table in the database
 * 
 * Prag potrosnje definise broj nagradnih bodova koje kupac dobije 
 * u zavisnosti od svoje kategorije i potrosenog novca za jednu kupovinu
 *
 */
@Entity
public class ConsumptionThreshold {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Double minSpending;
	
	private Double maxSpending;
	
	@OneToOne(mappedBy = "consumptionTreshold", cascade=CascadeType.ALL)
	private BuyerCategory buyerCategory;
	
	//Kreiraj ovde i funkciju za dodelu bodova kad dodje vreme za to
	
	public ConsumptionThreshold(){}
	
	public ConsumptionThreshold(Double minSpending, Double maxSpending, BuyerCategory buyerCategory) {
		super();
		this.minSpending = minSpending;
		this.maxSpending = maxSpending;
		this.buyerCategory = buyerCategory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMinSpending() {
		return minSpending;
	}

	public void setMinSpending(Double minSpending) {
		this.minSpending = minSpending;
	}

	public Double getMaxSpending() {
		return maxSpending;
	}

	public void setMaxSpending(Double maxSpending) {
		this.maxSpending = maxSpending;
	}

	public BuyerCategory getBuyerCategory() {
		return buyerCategory;
	}

	public void setBuyerCategory(BuyerCategory buyerCategory) {
		this.buyerCategory = buyerCategory;
	}

	@Override
	public String toString() {
		return minSpending + " - " + maxSpending;
	}
	
}
