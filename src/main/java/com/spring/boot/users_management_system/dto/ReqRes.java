package com.spring.boot.users_management_system.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.boot.users_management_system.entity.OurUsers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class ReqRes {
	
		private int statusCode;
	    private String error;
	    private String message;
	    private String token;
	    private String refreshToken;
	    private String expirationTime;

	    @NotEmpty(message = "Name cannot be empty")
	    private String name;

	    @NotEmpty(message = "City cannot be empty")
	    private String city;

	    @NotEmpty(message = "Role cannot be empty")
	    private String role;

	    @Email(message = "Please provide a valid email address")
	    @NotEmpty(message = "Email cannot be empty")
	    private String email;

	    @NotEmpty(message = "Password cannot be empty")
	    @Size(min = 6, message = "Password must have at least 6 characters")
	    private String password;

	    private OurUsers ourUsers;
	    private List<OurUsers> ourUsersList;

}
