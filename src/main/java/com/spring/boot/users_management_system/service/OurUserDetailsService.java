package com.spring.boot.users_management_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.boot.users_management_system.repository.UsersRepo;

@Service
public class OurUserDetailsService implements UserDetailsService {
	
	   @Autowired
	    private UsersRepo usersRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 return usersRepo.findByEmail(username).orElseThrow();
		
	}

}
