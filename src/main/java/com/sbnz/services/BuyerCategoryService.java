package com.sbnz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.model.BuyerCategory;
import com.sbnz.repository.BuyerCategoryRepository;

@Service
public class BuyerCategoryService {

	@Autowired
	BuyerCategoryRepository buyerCategoryRepository;
	
	/**
	 * Return all buyer categories from database
	 * @return List<BuyerCategory>
	 */
	public List<BuyerCategory> getAllBuyerCategories(){
		return buyerCategoryRepository.findAll();
	}
	
	/**
	 * Save Buyer category to database
	 * @param bc 
	 * @return BuyerCategory
	 */
	public BuyerCategory save(BuyerCategory bc){
		return buyerCategoryRepository.save(bc);
	}
	
	/**
	 * Find one BuyerCategory by its code
	 * @param code
	 * @return BuyerCategory
	 */
	public BuyerCategory findOneByCode(String code){
		return buyerCategoryRepository.findOneByCode(code);
	}
	
	/**
	 * Find one BuyerCategory by its name
	 * @param name
	 * @return BuyerCategory
	 */
	public BuyerCategory findOneByName(String name){
		return buyerCategoryRepository.findOneByName(name);
	}
	
	/**
	 * Delete Buyer Category by its id
	 * @param id
	 */
	public void delete(Long id){
		buyerCategoryRepository.delete(id);
	}
	
	/**
	 * Find BuyerCategory by its id
	 * @param id
	 * @return
	 */
	public BuyerCategory findOneById(Long id){
		return buyerCategoryRepository.findOne(id);
	}
}
