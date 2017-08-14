package com.sbnz.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 
 * @author Predrag Falcic
 * 
 * Creates BuyerCategory table in the database
 * 
 * Buyer can have one category at a time
 *
 */
@Entity
public class BuyerCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String code;
	
	private String name;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "consumption_threshold_id")
	private ConsumptionThreshold consumptionTreshold;
	
	@OneToMany(mappedBy="buyerCategory")
	private Collection<Profile> profiles;
	
	private int procentOfSpendingToPoints; // Procenat od korisnikove potrosnje koji se konvertuje u nagradne bodove
	
	public BuyerCategory(){}

	public BuyerCategory(String code, String name, ConsumptionThreshold consumptionTreshold,
			Collection<Profile> profiles, int procentOfSpendingToPoints) {
		super();
		this.code = code;
		this.name = name;
		this.consumptionTreshold = consumptionTreshold;
		this.profiles = profiles;
		this.procentOfSpendingToPoints = procentOfSpendingToPoints;
	}
	
	public BuyerCategory(BuyerCategory bc){
		this(bc.getCode(), bc.getName(), bc.getConsumptionTreshold(), bc.getProfiles(), bc.getProcentOfSpendingToPoints());
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

	public Collection<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(Collection<Profile> profiles) {
		this.profiles = profiles;
	}

	public ConsumptionThreshold getConsumptionTreshold() {
		return consumptionTreshold;
	}

	public void setConsumptionTreshold(ConsumptionThreshold consumptionTreshold) {
		this.consumptionTreshold = consumptionTreshold;
	}

	public int getProcentOfSpendingToPoints() {
		return procentOfSpendingToPoints;
	}

	public void setProcentOfSpendingToPoints(int procentOfSpendingToPoints) {
		this.procentOfSpendingToPoints = procentOfSpendingToPoints;
	}

	@Override
	public String toString() {
		return "BuyerCategory [id=" + id + ", code=" + code + ", name=" + name + ", consumptionTreshold="
				+ consumptionTreshold + ", profiles=" + profiles + ", procentOfSpendingToPoints="
				+ procentOfSpendingToPoints + "]";
	}
}
