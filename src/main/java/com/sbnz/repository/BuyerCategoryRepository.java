package com.sbnz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.model.BuyerCategory;

public interface BuyerCategoryRepository extends JpaRepository<BuyerCategory, Long>{
	BuyerCategory findOneByCode(String code);
	BuyerCategory findOneByName(String name);
}
