package com.sbnz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbnz.model.User;
import com.sbnz.services.UserService;

@Controller
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path="/add", method = RequestMethod.POST)
	public @ResponseBody User register(@RequestBody @Valid User user) {
		User result_user = userService.save(user);
		return result_user;
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userService.getAll();
	}
}
