package com.sbnz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.model.ConsumptionThreshold;
import com.sbnz.repository.ConsumptionTresholdRepository;

@Service
public class ConsumptionTresholdService {

	@Autowired
	private ConsumptionTresholdRepository ctRepository;
	
	public ConsumptionThreshold save(ConsumptionThreshold ct){
		return ctRepository.save(ct);
	}
	
	public ConsumptionThreshold findOneByMinSpending(double minSpending){
		return ctRepository.findOneByMinSpending(minSpending);
	}
	
	public ConsumptionThreshold findOneByMaxSpending(double maxSpending){
		return ctRepository.findOneByMaxSpending(maxSpending);
	}
}
