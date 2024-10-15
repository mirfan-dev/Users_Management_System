package com.spring.boot.users_management_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.spring.boot.users_management_system.dto.ReqRes;
import com.spring.boot.users_management_system.entity.OurUsers;
import com.spring.boot.users_management_system.service.UsersManagementService;

import jakarta.validation.Valid;

@RestController
public class UserManagementController {
	
	
	   @Autowired
	    private UsersManagementService usersManagementService;

	    @PostMapping("/auth/register")
	    public ResponseEntity<ReqRes> register( @RequestBody @Valid ReqRes reg){
	    	 return usersManagementService.register(reg);
	    }

	    @PostMapping("/auth/login")
	    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
	        return usersManagementService.login(req);
	    }

	    @PostMapping("/auth/refresh")
	    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
	        return ResponseEntity.ok(usersManagementService.refreshToken(req));
	    }

	    @GetMapping("/admin/get-all-users")
	    public ResponseEntity<ReqRes> getAllUsers(){
	        return ResponseEntity.ok(usersManagementService.getAllUsers());

	    }

	    @GetMapping("/admin/get-users/{id}")
	    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer id){
	    	return usersManagementService.getUsersById(id);

	    }

	    @PutMapping("/admin/update/{userId}")
	    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres){
	    	return usersManagementService.updateUser(userId, reqres);
	    }

	    @GetMapping("/adminuser/get-profile")
	    public ResponseEntity<ReqRes> getMyProfile(){
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String email = authentication.getName();
	        ReqRes response = usersManagementService.getMyInfo(email);
	        return  ResponseEntity.status(response.getStatusCode()).body(response);
	    }

	    @DeleteMapping("/admin/delete/{id}")
	    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer id){
	    	return usersManagementService.deleteUser(id);
	    }

}
