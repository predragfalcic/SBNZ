package com.sbnz.model;

import java.sql.Date;
import java.util.Collection;

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
	
	private Date beginActivePeriod; // Period za koj je akcijski dogadjaj aktivan, 27.06-27.07
	
	private Date endActivePeriod;
	
	private int discount; // Procentualni popust
	
	@ManyToMany(targetEntity = ArticalCategory.class)
    @JoinTable(name = "articalCategory_sales", 
    	joinColumns = {@JoinColumn(name = "sale_id")}, 
    	inverseJoinColumns = {@JoinColumn(name = "artical_category_id")})
	private Collection<ArticalCategory> articalCategoriesOnSale; // Lista kategorija artikala koje su na akciji
	
	@ManyToMany(mappedBy = "sales")
	private Collection<Bill> bills;
	
	@ManyToOne
	private BillItem billItem;
	
	public Sale(){}

	public Sale(Long id, String code, String name, Date beginActivePeriod, Date endActivePeriod, int discount,
			Collection<ArticalCategory> articalCategoriesOnSale, Collection<Bill> bills, BillItem billItem) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.beginActivePeriod = beginActivePeriod;
		this.endActivePeriod = endActivePeriod;
		this.discount = discount;
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

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
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

	public Date getBeginActivePeriod() {
		return beginActivePeriod;
	}

	public void setBeginActivePeriod(Date beginActivePeriod) {
		this.beginActivePeriod = beginActivePeriod;
	}

	public Date getEndActivePeriod() {
		return endActivePeriod;
	}

	public void setEndActivePeriod(Date endActivePeriod) {
		this.endActivePeriod = endActivePeriod;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", code=" + code + ", name=" + name + ", beginActivePeriod=" + beginActivePeriod
				+ ", endActivePeriod=" + endActivePeriod + ", Discount=" + discount + ", articalCategoriesOnSale="
				+ articalCategoriesOnSale + ", bills=" + bills + ", billItem=" + billItem + "]";
	}
}
