package com.sbnz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
