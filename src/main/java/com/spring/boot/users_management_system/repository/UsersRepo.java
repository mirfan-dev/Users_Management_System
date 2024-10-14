package com.spring.boot.users_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.users_management_system.entity.OurUsers;

public interface UsersRepo extends JpaRepository<OurUsers, Integer> {
	
	Optional<OurUsers> findByEmail(String email);
	
	Optional<OurUsers> findById(Integer id);


}
