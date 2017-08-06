package com.sbnz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.model.Role;
import com.sbnz.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role findRoleByName(String name){
		return roleRepository.findByName(name);
	}
	
	public Role save(Role role){
		return roleRepository.save(role);
	}
	
	public List<Role> getAll(){
		return roleRepository.findAll();
	}
}
