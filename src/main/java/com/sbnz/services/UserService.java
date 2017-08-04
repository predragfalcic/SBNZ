package com.sbnz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.model.User;
import com.sbnz.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	public User save(User user){
		return userRepository.save(user);
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
}
