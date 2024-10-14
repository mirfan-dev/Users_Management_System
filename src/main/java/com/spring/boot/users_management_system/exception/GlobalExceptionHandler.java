package com.spring.boot.users_management_system.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(value = MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> methodArgException(MethodArgumentNotValidException ex) {
	        
	        Map<String, String> errorMap = new HashMap<>();
	        ex.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
	        
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
	    }

	 
	 @ExceptionHandler(value = UserNotFoundException.class)
	    public ResponseEntity<Object> methodArgException(UserNotFoundException ex) {
		 
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	 }
	 

}
