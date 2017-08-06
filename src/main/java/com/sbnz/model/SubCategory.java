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
 * Subcategories table for database
 * Subcategories for categories
 * 
 */
@Entity
public class SubCategory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String name;
	
	@ManyToOne
	private ArticalCategory articalCategory;
	
	@OneToMany(mappedBy="subCategory")
	private Collection<Artical> articals;
}
