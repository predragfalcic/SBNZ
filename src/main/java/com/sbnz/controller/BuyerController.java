package com.sbnz.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sbnz.model.BuyerCategory;
import com.sbnz.model.ConsumptionThreshold;
import com.sbnz.model.User;
import com.sbnz.services.BuyerCategoryService;
import com.sbnz.services.ConsumptionTresholdService;
import com.sbnz.services.UserService;

@Controller
@RequestMapping(path="/")
public class BuyerController {

	@Autowired
	private BuyerCategoryService buyerCategoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConsumptionTresholdService ctService;
	
	@RequestMapping(value = "/user/{id}/buyercategories", method = RequestMethod.GET)
	public ModelAndView showBuyerCategories(ModelAndView modelAndView,
			HttpServletResponse response,
			@PathVariable Long id,
			@Valid BuyerCategory bc){
		
		User user = userService.findUserById(id);
		
		System.out.println("userRole: "+user.getRole().getName());
		
		// Only user with seler role can access this page
		if(!user.getRole().getName().equals("Seler")){
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
		}
		
		// Display page with buyer categories options
		modelAndView.setViewName("buyer_categories");
		
		modelAndView.addObject("user", user);
		
		modelAndView.addObject("bc", bc);
		
		// Get all buyer categories from database
		modelAndView.addObject("buyerCategories", buyerCategoryService.getAllBuyerCategories());
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/user/{id}/buyercategories", method = RequestMethod.POST)
	public ModelAndView addBuyerCategories(ModelAndView modelAndView,
			HttpServletResponse response,
			@RequestParam Map requestParams,
			@PathVariable Long id,
			@Valid BuyerCategory bc){
		
		User user = userService.findUserById(id);
		
		System.out.println("userRole: "+user.getRole().getName());
		
		// Only user with seler role can access this page
		if(!user.getRole().getName().equals("Seler")){
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
		}
		
		ConsumptionThreshold ct = new ConsumptionThreshold();
		ct.setMinSpending(Double.parseDouble(requestParams.get("minConsumption").toString()));
		ct.setMaxSpending(Double.parseDouble(requestParams.get("maxConsumption").toString()));
		ctService.save(ct);
		
		// Check if buyer category with that code exists
		if(buyerCategoryService.findOneByCode(bc.getCode()) != null){
			
			// Display page with buyer categories options
			modelAndView.setViewName("buyer_categories");
						
			modelAndView.addObject("errorMessage", "Buyer Category with that code already exists.");
			
			modelAndView.addObject("user", user);
			
			modelAndView.addObject("bc", bc);
			
			// Get all buyer categories from database
			modelAndView.addObject("buyerCategories", buyerCategoryService.getAllBuyerCategories());
			
			return modelAndView;
		}
		
		// Check if buyer category with that name exists
		if(buyerCategoryService.findOneByName(bc.getName()) != null){
			
			// Display page with buyer categories options
			modelAndView.setViewName("buyer_categories");
			
			modelAndView.addObject("errorMessage", "Buyer Category with that name already exists.");
			
			modelAndView.addObject("user", user);
			
			modelAndView.addObject("bc", bc);
			
			// Get all buyer categories from database
			modelAndView.addObject("buyerCategories", buyerCategoryService.getAllBuyerCategories());
			
			return modelAndView;
		}
		
		bc.setConsumptionTreshold(ct);
		
		// Save Buyer Category to database
		BuyerCategory resultBC = buyerCategoryService.save(bc);
		
		// Display page with buyer categories options
		modelAndView.setViewName("buyer_categories");
		
		modelAndView.addObject("user", user);
		
		modelAndView.addObject("bc", bc);
		
		// Get all buyer categories from database
		modelAndView.addObject("buyerCategories", buyerCategoryService.getAllBuyerCategories());
		
		return modelAndView;
		
	}
}
