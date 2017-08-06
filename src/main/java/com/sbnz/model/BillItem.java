package com.sbnz.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 
 * @author Predrag Falcic
 *
 * Create bill item table in database
 * 
 */
@Entity
public class BillItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Bill bill;
	
	private int numBillItem; // Redni broj stavke racuna
							 // Jedinstveno u okviru racuna
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artical_id")
	private Artical artical;
	
	private Double articalPrize; // Cena artikla na dan prodaje
	
	private int numOfArticalsBought; // Kolicina kupljenih artikala
	
	private Double originalBillItemSumPrize; // Originalna ukupna cena stavke racuna
	
	private Double discountPercent; // procenat umanjenja
	
	private Double finalBillItemPrize; // Cena stavke racuna nakon svih popusta
	
	@OneToMany(mappedBy="billItem")
	private Collection<Sale> sales; // Lista svih primenjenih popusta
	
	@ManyToOne
	private BillItemDiscount billItemDiscount;
	
	public BillItem(){}
	
	public BillItem(Bill bill, int numBillItem, Artical artical, Double articalPrize, int numOfArticalsBought,
			Double originalBillItemSumPrize, Double discountPercent, Double finalBillItemPrize, Collection<Sale> sales,
			BillItemDiscount billItemDiscount) {
		super();
		this.bill = bill;
		this.numBillItem = numBillItem;
		this.artical = artical;
		this.articalPrize = articalPrize;
		this.numOfArticalsBought = numOfArticalsBought;
		this.originalBillItemSumPrize = originalBillItemSumPrize;
		this.discountPercent = discountPercent;
		this.finalBillItemPrize = finalBillItemPrize;
		this.sales = sales;
		this.billItemDiscount = billItemDiscount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public int getNumBillItem() {
		return numBillItem;
	}

	public void setNumBillItem(int numBillItem) {
		this.numBillItem = numBillItem;
	}

	public Artical getArtical() {
		return artical;
	}

	public void setArtical(Artical artical) {
		this.artical = artical;
	}

	public Double getArticalPrize() {
		return articalPrize;
	}

	public void setArticalPrize(Double articalPrize) {
		this.articalPrize = articalPrize;
	}

	public int getNumOfArticalsBought() {
		return numOfArticalsBought;
	}

	public void setNumOfArticalsBought(int numOfArticalsBought) {
		this.numOfArticalsBought = numOfArticalsBought;
	}

	public Double getOriginalBillItemSumPrize() {
		return originalBillItemSumPrize;
	}

	public void setOriginalBillItemSumPrize(Double originalBillItemSumPrize) {
		this.originalBillItemSumPrize = originalBillItemSumPrize;
	}

	public Double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Double getFinalBillItemPrize() {
		return finalBillItemPrize;
	}

	public void setFinalBillItemPrize(Double finalBillItemPrize) {
		this.finalBillItemPrize = finalBillItemPrize;
	}

	public Collection<Sale> getSales() {
		return sales;
	}

	public void setSales(Collection<Sale> sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "BillItem [id=" + id + ", bill=" + bill + ", numBillItem=" + numBillItem + ", artical=" + artical
				+ ", articalPrize=" + articalPrize + ", numOfArticalsBought=" + numOfArticalsBought
				+ ", originalBillItemSumPrize=" + originalBillItemSumPrize + ", discountPercent=" + discountPercent
				+ ", finalBillItemPrize=" + finalBillItemPrize + ", sales=" + sales + ", billItemDiscount="
				+ billItemDiscount + "]";
	}
	
}









