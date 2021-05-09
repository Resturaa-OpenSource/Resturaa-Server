package com.auri.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auri.dao.StaffRepo;
import com.auri.entity.StaffTable;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	StaffRepo staffRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		
//		System.out.println("usernameOrEmail  loadUserByUsername "+usernameOrEmail);

		StaffTable user = staffRepo.findByStaffUserName(usernameOrEmail).get();
		
//		System.out.println("rintu " +user.getStaffUserName());
		
		return UserPrincipal.create(user);
	}

	// This method is used by JWTAuthenticationFilter
	@Transactional
	public UserDetails loadUserById(Long userId) {
		System.out.println(userId);
		StaffTable user = staffRepo.findByStaffID(userId).get();
				
		return UserPrincipal.create(user);
	}
}
