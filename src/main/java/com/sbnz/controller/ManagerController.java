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

import com.sbnz.model.ArticalCategory;
import com.sbnz.model.BuyerCategory;
import com.sbnz.model.ConsumptionThreshold;
import com.sbnz.model.User;
import com.sbnz.services.ArticalCategoryService;
import com.sbnz.services.BuyerCategoryService;
import com.sbnz.services.ConsumptionTresholdService;
import com.sbnz.services.UserService;

@Controller
@RequestMapping(path="/")
public class ManagerController {

	@Autowired
	private BuyerCategoryService buyerCategoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConsumptionTresholdService ctService;
	
	@Autowired 
	private ArticalCategoryService acService;
	
	/**
	 * Display table with all Buyer Categories and form for adding new Buyer Category
	 * @param modelAndView
	 * @param response
	 * @param id
	 * @param bc
	 * @return
	 */
	@RequestMapping(value = "/user/{id}/buyercategories", method = RequestMethod.GET)
	public ModelAndView showBuyerCategories(ModelAndView modelAndView,
			HttpServletResponse response,
			@PathVariable Long id,
			@Valid BuyerCategory bc){
		
		User user = userService.findUserById(id);
		
		System.out.println("userRole: "+user.getRole().getName());
		
		// Only user with seler role can access this page
		if(!user.getRole().getName().equals("Manager")){
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
	
	/**
	 * Save Buyer Category to the database
	 * @param modelAndView
	 * @param response
	 * @param requestParams
	 * @param id
	 * @param bc
	 * @return
	 */
	@RequestMapping(value = "/user/{id}/buyercategories", method = RequestMethod.POST)
	public ModelAndView addBuyerCategories(ModelAndView modelAndView,
			HttpServletResponse response,
			@RequestParam Map requestParams,
			@PathVariable Long id,
			@Valid BuyerCategory bc){
		
		User user = userService.findUserById(id);
		
		System.out.println("userRole: "+user.getRole().getName());
		
		// Only user with seler role can access this page
		if(!user.getRole().getName().equals("Manager")){
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
	
	/**
	 * Delete Buyer Category from database
	 * @param modelAndView
	 * @param response
	 * @param requestParams
	 * @param id
	 * @param bc_id
	 * @param bc
	 * @return
	 */
	@RequestMapping(value = "/user/{id}/buyercategories/{bc_id}/delete", method = RequestMethod.GET)
	public ModelAndView deleteBuyerCategories(ModelAndView modelAndView,
			HttpServletResponse response,
			@RequestParam Map requestParams,
			@PathVariable Long id,
			@PathVariable Long bc_id,
			@Valid BuyerCategory bc){
		
		User user = userService.findUserById(id);
		
		System.out.println("userRole: "+user.getRole().getName());
		
		// Only user with seler role can access this page
		if(!user.getRole().getName().equals("Manager")){
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
		}
		
		// Delete Buyer Category from database
		buyerCategoryService.delete(bc_id);
		
		// Display page with buyer categories options
		modelAndView.setViewName("buyer_categories");
		
		modelAndView.addObject("user", user);
		
		modelAndView.addObject("bc", bc);
		
		// Get all buyer categories from database
		modelAndView.addObject("buyerCategories", buyerCategoryService.getAllBuyerCategories());
		
		return modelAndView;
		
	}
	
	/**
	 * Display the edit BuyerCategory page with form to edit it
	 * @param modelAndView
	 * @param response
	 * @param requestParams
	 * @param id
	 * @param bc_id
	 * @param bc
	 * @return
	 */
	@RequestMapping(value = "/user/{id}/buyercategories/{bc_id}/edit", method = RequestMethod.GET)
	public ModelAndView showEditBuyerCategories(ModelAndView modelAndView,
			HttpServletResponse response,
			@RequestParam Map requestParams,
			@PathVariable Long id,
			@PathVariable Long bc_id,
			@Valid BuyerCategory bc){
		
		User user = userService.findUserById(id);
		
		System.out.println("userRole: "+user.getRole().getName());
		
		// Only user with seler role can access this page
		if(!user.getRole().getName().equals("Manager")){
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
		}
		
		// Find Buyer Category to edit from database
		BuyerCategory result_bc = buyerCategoryService.findOneById(bc_id);
		
		// Display page with form to edit selected Buyer Category
		modelAndView = new ModelAndView("editBuyerCategory");
		
		modelAndView.addObject("user", user);
		
		modelAndView.addObject("bc", result_bc);
		
		return modelAndView;
		
	}
	
	/**
	 * Save the edited BuyerCategory to database
	 * @param modelAndView
	 * @param response
	 * @param requestParams
	 * @param id
	 * @param bc_id
	 * @param bc
	 * @return
	 */
	@RequestMapping(value = "/user/{id}/buyercategories/{bc_id}/edit", method = RequestMethod.POST)
	public ModelAndView EditBuyerCategories(ModelAndView modelAndView,
			HttpServletResponse response,
			@RequestParam Map requestParams,
			@PathVariable Long id,
			@PathVariable Long bc_id,
			@Valid BuyerCategory bc){
		
		User user = userService.findUserById(id);
		
		System.out.println("userRole: "+user.getRole().getName());
		
		// Only user with seler role can access this page
		if(!user.getRole().getName().equals("Manager")){
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
		}
		
		// Display page with form to edit selected Buyer Category
		modelAndView = new ModelAndView("editBuyerCategory");
				
		modelAndView.addObject("user", user);
		
		// Save the data for the edited Buyer Category
		BuyerCategory bcToUpdate = buyerCategoryService.findOneById(bc_id);
		
		System.out.println(bcToUpdate.toString());
		System.out.println(bc.toString());
		
		bcToUpdate.setId(bc_id);
		bcToUpdate.setCode(bc.getCode());
		bcToUpdate.setName(bc.getName());
		bcToUpdate.setConsumptionTreshold(bc.getConsumptionTreshold());
		bcToUpdate.setProcentOfSpendingToPoints(bc.getProcentOfSpendingToPoints());
		
		
		BuyerCategory bcEdited = buyerCategoryService.save(bcToUpdate);
		
		modelAndView.addObject("bc", bcEdited);
		
		modelAndView.addObject("successMessage", "Buyer Category has been changed.");
		
		return modelAndView;
	}
	
	
	// Adding artical categories controllers
	
	/**
	 * Display all Artical categories and form to add new one
	 * @param modelAndView
	 * @param response
	 * @param id
	 * @param ac
	 * @return
	 */
	@RequestMapping(value = "/user/{id}/articalcategories", method = RequestMethod.GET)
	public ModelAndView showArticalCategories(ModelAndView modelAndView,
			HttpServletResponse response,
			@PathVariable Long id,
			@Valid ArticalCategory ac){
		
		User user = userService.findUserById(id);
		
		System.out.println("userRole: "+user.getRole().getName());
		
		// Only user with seler role can access this page
		if(!user.getRole().getName().equals("Manager")){
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
		}
		
		// Display page with buyer categories options
		modelAndView.setViewName("artical_categories");
		
		modelAndView.addObject("user", user);
		
		modelAndView.addObject("ac", ac);
		
		// Get all buyer categories from database
		modelAndView.addObject("articalCategories", acService.getAllArticalCategories());
		
		return modelAndView;
	}
	
	/**
	 * Save new Artical Category to database
	 * @param modelAndView
	 * @param response
	 * @param requestParams
	 * @param id
	 * @param ac
	 * @return
	 */
	@RequestMapping(value = "/user/{id}/articalcategories", method = RequestMethod.POST)
	public ModelAndView addArticalCategories(ModelAndView modelAndView,
			HttpServletResponse response,
			@RequestParam Map requestParams,
			@PathVariable Long id,
			@Valid ArticalCategory ac){
		
		User user = userService.findUserById(id);
		
		System.out.println("userRole: "+user.getRole().getName());
		
		// Only user with seler role can access this page
		if(!user.getRole().getName().equals("Manager")){
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
		}
		
		// Check if artical category with that code exists
		if(acService.findOneByCode(ac.getCode()) != null){
			
			// Display page with artical categories options
			modelAndView.setViewName("artical_categories");
						
			modelAndView.addObject("errorMessage", "Artical Category with that code already exists.");
			
			modelAndView.addObject("user", user);
			
			modelAndView.addObject("ac", ac);
			
			// Get all artical categories from database
			modelAndView.addObject("articalCategories", acService.getAllArticalCategories());
			
			return modelAndView;
		}
		
		// Check if artical category with that name exists
		if(acService.findOneByName(ac.getName()) != null){
			
			// Display page with artical categories options
			modelAndView.setViewName("artical_categories");
			
			modelAndView.addObject("errorMessage", "Artical Category with that name already exists.");
			
			modelAndView.addObject("user", user);
			
			modelAndView.addObject("ac", ac);
			
			// Get all artical categories from database
			modelAndView.addObject("articalCategories", acService.getAllArticalCategories());
			
			return modelAndView;
		}
		
		// Save Artical Category to database
		ArticalCategory resultAC = acService.save(ac);
		
		// Display page with buyer categories options
		modelAndView.setViewName("artical_categories");
		
		modelAndView.addObject("user", user);
		
		modelAndView.addObject("ac", ac);
		
		// Get all artical categories from database
		modelAndView.addObject("articalCategories", acService.getAllArticalCategories());
		
		return modelAndView;
		
	}
	
	/**
	 * Delete Artical Category from database
	 * @param modelAndView
	 * @param response
	 * @param requestParams
	 * @param id
	 * @param ac_id
	 * @param ac
	 * @return
	 */
	@RequestMapping(value = "/user/{id}/articalcategories/{ac_id}/delete", method = RequestMethod.GET)
	public ModelAndView deleteArticalCategories(ModelAndView modelAndView,
			HttpServletResponse response,
			@RequestParam Map requestParams,
			@PathVariable Long id,
			@PathVariable Long ac_id,
			@Valid ArticalCategory ac){
		
		User user = userService.findUserById(id);
		
		System.out.println("userRole: "+user.getRole().getName());
		
		// Only user with seler role can access this page
		if(!user.getRole().getName().equals("Manager")){
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
		}
		
		// Delete Artical Category from database
		acService.delete(ac_id);
		
		// Display page with artical categories options
		modelAndView.setViewName("artical_categories");
		
		modelAndView.addObject("user", user);
		
		modelAndView.addObject("ac", ac);
		
		// Get all buyer categories from database
		modelAndView.addObject("articalCategories", acService.getAllArticalCategories());
		
		return modelAndView;
	}
	
	/**
	 * Display the edit form for selected artical category to edit
	 * @param modelAndView
	 * @param response
	 * @param requestParams
	 * @param id
	 * @param ac_id
	 * @param ac
	 * @return
	 */
	@RequestMapping(value = "/user/{id}/articalcategories/{ac_id}/edit", method = RequestMethod.GET)
	public ModelAndView showEditArticalCategories(ModelAndView modelAndView,
			HttpServletResponse response,
			@RequestParam Map requestParams,
			@PathVariable Long id,
			@PathVariable Long ac_id,
			@Valid ArticalCategory ac){
		
		User user = userService.findUserById(id);
		
		System.out.println("userRole: "+user.getRole().getName());
		
		// Only user with seler role can access this page
		if(!user.getRole().getName().equals("Manager")){
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
		}
		
		// Find Artical Category to edit from database
		ArticalCategory result_ac = acService.findOneById(ac_id);
		
		// Display page with form to edit selected Artical Category
		modelAndView = new ModelAndView("editArticalCategory");
		
		modelAndView.addObject("user", user);
		
		modelAndView.addObject("ac", result_ac);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/user/{id}/articalcategories/{ac_id}/edit", method = RequestMethod.POST)
	public ModelAndView EditArticalCategories(ModelAndView modelAndView,
			HttpServletResponse response,
			@RequestParam Map requestParams,
			@PathVariable Long id,
			@PathVariable Long ac_id,
			@Valid ArticalCategory ac){
		
		User user = userService.findUserById(id);
		
		System.out.println("userRole: "+user.getRole().getName());
		
		// Only user with seler role can access this page
		if(!user.getRole().getName().equals("Manager")){
			response.setStatus( HttpServletResponse.SC_BAD_REQUEST );
		}
		
		// Display page with form to edit selected Artical Category
		modelAndView = new ModelAndView("editArticalCategory");
				
		modelAndView.addObject("user", user);
		
		// Save the data for the edited Artical Category
		ArticalCategory acToUpdate = acService.findOneById(ac_id);
		
		System.out.println(acToUpdate.toString());
		System.out.println(ac.toString());
		
		acToUpdate.setId(ac_id);
		acToUpdate.setCode(ac.getCode());
		acToUpdate.setName(ac.getName());
		acToUpdate.setMaxDiscount(ac.getMaxDiscount());
		
		
		ArticalCategory acEdited = acService.save(acToUpdate);
		
		modelAndView.addObject("ac", acEdited);
		
		modelAndView.addObject("successMessage", "Artical Category has been changed.");
		
		return modelAndView;
	}
}


















