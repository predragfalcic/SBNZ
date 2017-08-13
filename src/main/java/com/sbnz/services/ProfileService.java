package com.sbnz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.model.Profile;
import com.sbnz.repository.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	/**
	 * Save profile to database
	 * @param profile
	 * @return Profile
	 */
	public Profile save(Profile profile){
		return profileRepository.save(profile);
	}
	
}
