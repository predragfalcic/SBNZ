package com.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.model.ArticalCategory;

public interface ArticalCategoryRepository extends JpaRepository<ArticalCategory, Long>{
	ArticalCategory findOneByCode(String code);
	ArticalCategory findOneByName(String name);
}
