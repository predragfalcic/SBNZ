package com.sbnz.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	
	private String kategorijaArtikla; // Mozda treba napraviti novu klasu za kategorije
	
	private Double prize;
	
	private int inStock;
	
	private String state; // Aktivan ili arhiviran

	@ManyToMany(mappedBy = "articals")
	private Set<Bill> bills;
	
	public Artical(){}
	
	public Artical(String code, String name, String kategorijaArtikla, Double prize, int inStock, String state) {
		super();
		this.code = code;
		this.name = name;
		this.kategorijaArtikla = kategorijaArtikla;
		this.prize = prize;
		this.inStock = inStock;
		this.state = state;
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

	public String getKategorijaArtikla() {
		return kategorijaArtikla;
	}

	public void setKategorijaArtikla(String kategorijaArtikla) {
		this.kategorijaArtikla = kategorijaArtikla;
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
	
	public Set<Bill> getBills() {
		return bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	@Override
	public String toString() {
		return "Artikal [id=" + id + ", code=" + code + ", name=" + name + ", kategorijaArtikla=" + kategorijaArtikla
				+ ", prize=" + prize + ", inStock=" + inStock + ", state=" + state + "]";
	}
}
