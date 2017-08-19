package com.sbnz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.model.Sale;
import com.sbnz.repository.SaleRepository;

@Service
public class SaleService {
	
	@Autowired
	SaleRepository saleRepository;
	
	public List<Sale> getAllSales(){
		return saleRepository.findAll();
	}
	
	public Sale save(Sale sale){
		return saleRepository.save(sale);
	}
	
	public Sale findOneById(Long id){
		return saleRepository.findOne(id);
	}
	
	public Sale findOneByCode(String code){
		return saleRepository.findOneByCode(code);
	}
	
	public Sale findOneyName(String name){
		return saleRepository.findOneByName(name);
	}
	
	public void delete(Long id){
		saleRepository.delete(id);
	}
}
