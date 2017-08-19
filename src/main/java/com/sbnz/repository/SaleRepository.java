package com.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long>{

	Sale findOneByCode(String code);
	Sale findOneByName(String name);
}
