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
	
	/**
	 * Find one user by his username
	 * @param username
	 * @return User
	 */
	public User findUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	/**
	 * Find user by its id
	 * @param id
	 * @return User
	 */
	public User findUserById(Long id){
		return userRepository.findOne(id);
	}
	
	/**
	 * Save user to database
	 * @param user
	 * @return User
	 */
	public User save(User user){
		return userRepository.save(user);
	}
	
	/**
	 * Returns all users from database
	 * @return List<User>
	 */
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	/**
	 * Return user by confirmation token
	 * @param confirmationToken 
	 * @return User
	 */
	public User findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}
}
