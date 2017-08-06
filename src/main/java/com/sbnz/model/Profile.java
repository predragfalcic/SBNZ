package com.sbnz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 
 * @author Predrag Falcic
 * 
 * Create Profile table in database
 * 
 * User with the role buyer can have Profile
 * 
 */
@Entity
public class Profile implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne(mappedBy = "profile")
	private User user;
	
	@ManyToOne
	private BuyerCategory buyerCategory;
	
	public Profile(){}

	public Profile(User user) {
		super();
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(mappedBy = "profile")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", user=" + user + "]";
	}
	
	
}
