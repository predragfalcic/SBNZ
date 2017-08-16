package com.sbnz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.model.ArticalCategory;
import com.sbnz.repository.ArticalCategoryRepository;

@Service
public class ArticalCategoryService {

	@Autowired
	private ArticalCategoryRepository acRepository;
	
	public List<ArticalCategory> getAllArticalCategories(){
		return acRepository.findAll();
	}
	
	public ArticalCategory findOneByCode(String code){
		return acRepository.findOneByCode(code);
	}
	
	public ArticalCategory findOneByName(String name){
		return acRepository.findOneByName(name);
	}
	
	public ArticalCategory save(ArticalCategory ac){
		return acRepository.save(ac);
	}
	
	public void delete(Long id){
		acRepository.delete(id);
	}
	
	public ArticalCategory findOneById(Long id){
		return acRepository.findOne(id);
	}
}
