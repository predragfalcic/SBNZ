package com.sbnz.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import com.sbnz.model.Profile;
import com.sbnz.model.Role;
import com.sbnz.model.User;
import com.sbnz.services.EmailService;
import com.sbnz.services.ProfileService;
import com.sbnz.services.RoleService;
import com.sbnz.services.UserService;

@Controller
@RequestMapping(path="/user")
public class UserController {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ProfileService profileService;
	
	/**
	 * Display logged users profile page
	 * @param modelAndView
	 * @param user
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView welcome(ModelAndView modelAndView, User user){
		modelAndView.addObject("user", user);
		modelAndView.setViewName("welcome");
		return modelAndView;
	}
	
	/**
	 * Display home page with registration form on it
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView home(ModelAndView modelAndView, User user){
		modelAndView.addObject("user", user);
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	/**
	 * Register the user by saving him to database
	 * @param modelAndView
	 * @param user
	 * @param bindingResult
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, 
			@Valid User user, 
			BindingResult bindingResult, 
			HttpServletRequest request){
		
		//Find the user by username(email address)
		User resultUser = userService.findUserByUsername(user.getUsername());
		
		if(resultUser != null){
			modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("home");
			bindingResult.reject("username");
		}
		
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("home");		
		} else { // new user , so we create user and send confirmation e-mail
			
			// Disable user until they click on confirmation link in email
		    user.setEnabled(false);
		      
		    // Generate random 36-character string token for confirmation link
		    user.setConfirmationToken(UUID.randomUUID().toString());
		    
		    // Set users role to Buyer
		    Role role = roleService.findRoleByName("Buyer");
		    user.setRole(role);
		    
		    userService.save(user);
			
			String appUrl = request.getScheme() + "://" + request.getServerName();
			
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getUsername());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
					+ appUrl + ":8080/user/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("noreply@domain.com");
			
			emailService.sendEmail(registrationEmail);
			
			modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getUsername());
			
			modelAndView.setViewName("home");
		}
		
		return modelAndView;
	}
	
	/**
	 * Process confirmation link
	 * @param modelAndView
	 * @param token
	 * @return ModelAndView
	 */
	@RequestMapping(value="/confirm", method = RequestMethod.GET)
	public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {
			
		User user = userService.findByConfirmationToken(token);
		
		if (user == null) { // No token found in DB
			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
		} else { // Token found
			modelAndView.addObject("confirmationToken", user.getConfirmationToken());
		}
			
		modelAndView.setViewName("confirm");
		return modelAndView;		
	}
		
	/**
	 * Process confirmation link
	 * @param modelAndView
	 * @param bindingResult
	 * @param requestParams
	 * @param redir
	 * @return
	 */
	@RequestMapping(value="/confirm", method = RequestMethod.POST)
	public ModelAndView processConfirmationForm(ModelAndView modelAndView, 
			BindingResult bindingResult, 
			@RequestParam Map requestParams, 
			RedirectAttributes redir) {
		
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		modelAndView.setViewName("confirm");
	
		Zxcvbn passwordCheck = new Zxcvbn();
		
		Strength strength = passwordCheck.measure(requestParams.get("password").toString());
		
		if (strength.getScore() < 3) {
			bindingResult.reject("password");
			
			redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");

			modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
			return modelAndView;
		}
		
		// Find the user associated with the reset token
		User user = userService.findByConfirmationToken(requestParams.get("token").toString());
		
		// Set new password
		user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password").toString()));
		
		// Set user to enabled
		user.setEnabled(true);
		
		// Create profile for the user and save the profile to database
		Profile profile = new Profile(user);
		profileService.save(profile);
		
		// Set users profile
		user.setProfile(profile);
		
		// Save user
		userService.save(user);
		
		modelAndView.addObject("successMessage", "Your password has been set!");
		return modelAndView;		
	}
	
	
	/**
	 * Display the login form to the user
	 * @param modelAndView
	 * @param user
	 * @return ModelAndView
	 */
	@RequestMapping(path="/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView modelAndView, User user){
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	/**
	 * Check if the entered data is correct and if the user exists
	 * If so than login the user
	 * @param modelAndView
	 * @param bindingResult
	 * @param requestParams
	 * @param redir
	 * @param user
	 * @return ModelAndView
	 */
	@RequestMapping(path="/login", method = RequestMethod.POST)
	public ModelAndView processLogin(ModelAndView modelAndView, 
			BindingResult bindingResult,
			@RequestParam Map requestParams, 
			RedirectAttributes redir,
			@Valid User user){
		
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		modelAndView.setViewName("login");
		
		// Find the user associated with the reset token
		User resultUser = userService.findUserByUsername(requestParams.get("username").toString());
		
		if(resultUser == null){
			
			modelAndView.addObject("errorMessage", "User with that username does not exist.");
			
			return modelAndView;
		}
		
		// Check password
		if(!bCryptPasswordEncoder.matches(requestParams.get("password").toString(), resultUser.getPassword())){
			
			modelAndView.addObject("errorMessage", "Entered password is invalid.");
			
			return modelAndView;
		}
		
		
		// Check if the user is enabled
		if(!resultUser.getEnabled()){
			
			modelAndView.addObject("errorMessage", "User does not exist.");
			
			return modelAndView;
		}
		
		if(resultUser.getRole().getName().equals("Buyer")){
			modelAndView = new ModelAndView("welcome");
		}else if(resultUser.getRole().getName().equals("Manager")){
			modelAndView = new ModelAndView("ManagerProfile");
		}else{
			modelAndView = new ModelAndView("SelerProfile");
		}
		
		modelAndView.addObject("user", resultUser);
		return modelAndView;	
	}
}
