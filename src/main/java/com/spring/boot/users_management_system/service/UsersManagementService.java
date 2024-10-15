package com.spring.boot.users_management_system.service;



import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.boot.users_management_system.dto.ReqRes;
import com.spring.boot.users_management_system.entity.OurUsers;
import com.spring.boot.users_management_system.exception.UserNotFoundException;
import com.spring.boot.users_management_system.repository.UsersRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UsersManagementService {
	
	
	 @Autowired
	    private UsersRepo usersRepo;
	    @Autowired
	    private JWTUtils jwtUtils;
	    @Autowired
	    private AuthenticationManager authenticationManager;
	    @Autowired
	    private PasswordEncoder passwordEncoder;


	    public ResponseEntity<ReqRes> register(ReqRes registrationRequest){
	        ReqRes resp = new ReqRes();


	            OurUsers ourUser = new OurUsers();
	            ourUser.setEmail(registrationRequest.getEmail());
	            ourUser.setCity(registrationRequest.getCity());
	            ourUser.setRole(registrationRequest.getRole());
	            ourUser.setName(registrationRequest.getName());
	            ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
	            OurUsers ourUsersResult = usersRepo.save(ourUser);
	            if (ourUsersResult.getId()>0) {
	                resp.setOurUsers((ourUsersResult));
	                resp.setMessage("User Saved Successfully");
	                resp.setStatusCode(HttpStatus.CREATED.value());
	                return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	                
	            }else {
	                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
	                resp.setError("User could not be saved");
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	            }


			
		
	
	    }


	    public ResponseEntity<ReqRes> login(ReqRes loginRequest){
	        ReqRes response = new ReqRes();
	        try {
	            authenticationManager
	                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
	                            loginRequest.getPassword()));
	            var user = usersRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
	            var jwt = jwtUtils.generateToken(user);
	            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
	            
	            response.setToken(jwt);
	            response.setRole(user.getRole());
	            response.setRefreshToken(refreshToken);
	            response.setExpirationTime("24Hrs");
	            response.setMessage("Successfully Logged In");
	            response.setStatusCode(HttpStatus.OK.value());
	            return ResponseEntity.ok(response); // Use ResponseEntity.ok() for better readability

	        }catch (Exception e){
	        	response.setStatusCode(HttpStatus.BAD_REQUEST.value());
	            response.setMessage("Invalid Username and password");
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
	        }
	       
	    }





	    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
	        ReqRes response = new ReqRes();
	        try{
	            String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
	            OurUsers users = usersRepo.findByEmail(ourEmail).orElseThrow();
	            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
	                var jwt = jwtUtils.generateToken(users);
	                response.setStatusCode(200);
	                response.setToken(jwt);
	                response.setRefreshToken(refreshTokenReqiest.getToken());
	                response.setExpirationTime("24Hr");
	                response.setMessage("Successfully Refreshed Token");
	            }
	            response.setStatusCode(200);
	            return response;

	        }catch (Exception e){
	            response.setStatusCode(500);
	            response.setMessage(e.getMessage());
	            return response;
	        }
	    }


	    public ReqRes getAllUsers() {
	        ReqRes reqRes = new ReqRes();

	        try {
	            List<OurUsers> result = usersRepo.findAll();
	            if (!result.isEmpty()) {
	                reqRes.setOurUsersList(result);
	                reqRes.setStatusCode(200);
	                reqRes.setMessage("Successful");
	            } else {
	                reqRes.setStatusCode(404);
	                reqRes.setMessage("No users found");
	            }
	            return reqRes;
	        } catch (Exception e) {
	            reqRes.setStatusCode(500);
	            reqRes.setMessage("Error occurred: " + e.getMessage());
	            return reqRes;
	        }
	    }


	    public ResponseEntity<ReqRes> getUsersById(Integer id) {
	        ReqRes reqRes = new ReqRes();
	        try {
	            OurUsers usersById = usersRepo.findById(id)
	                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	            
	            reqRes.setOurUsers(usersById);
	            reqRes.setStatusCode(HttpStatus.OK.value());
	            reqRes.setMessage("User with id '" + id + "' found successfully");
	            return ResponseEntity.ok(reqRes); // Return 200 OK response with user data
	        
	        } catch (Exception e) {
	            reqRes.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());  
	            reqRes.setMessage("User not found with id : "+id);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reqRes); // Return 500 Internal Server Error
	        }
	    }



	    public ResponseEntity<ReqRes> deleteUser(Integer id) {
	        ReqRes reqRes = new ReqRes();
	        try {
	            OurUsers user = usersRepo.findById(id)
	                    .orElseThrow(() -> new UserNotFoundException("User not found for deletion with id: " + id));
	            
	            usersRepo.deleteById(id);
	            reqRes.setMessage("User deleted successfully");
	            reqRes.setStatusCode(HttpStatus.OK.value());
	            return ResponseEntity.ok(reqRes); // Use ResponseEntity.ok() for better readability
	        }  catch (Exception e) {
	            reqRes.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            reqRes.setMessage("User not found with id : "+id);
	  
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reqRes); // Return 500 response
	        }
	    }



	    public ResponseEntity<ReqRes> updateUser(Integer userId, OurUsers updatedUser) {
	        ReqRes reqRes = new ReqRes();
	        try {
	            OurUsers existingUser = usersRepo.findById(userId)
	                    .orElseThrow(() -> new UserNotFoundException("User not found for update with id: " + userId));

	            // Update user fields
	            existingUser.setEmail(updatedUser.getEmail());
	            existingUser.setName(updatedUser.getName());
	            existingUser.setCity(updatedUser.getCity());
	            existingUser.setRole(updatedUser.getRole());

	            // Check if password is present in the request
	            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
	                // Encode the password and update it
	                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
	            }

	            OurUsers savedUser = usersRepo.save(existingUser);
	            reqRes.setOurUsers(savedUser);
	            reqRes.setMessage("User updated successfully");
	            reqRes.setStatusCode(HttpStatus.OK.value());
	            return ResponseEntity.ok(reqRes); // Use ResponseEntity.ok() for better readability
	        } catch (Exception e) {
	            reqRes.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            reqRes.setMessage("User not found with id : "+userId);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reqRes); // Return 500 response
	        }
	    }




	    public ReqRes getMyInfo(String email){
	        ReqRes reqRes = new ReqRes();
	        try {
	            Optional<OurUsers> userOptional = usersRepo.findByEmail(email);
	            if (userOptional.isPresent()) {
	                reqRes.setOurUsers(userOptional.get());
	                reqRes.setStatusCode(200);
	                reqRes.setMessage("successful");
	            } else {
	                reqRes.setStatusCode(404);
	                reqRes.setMessage("User not found for update");
	            }

	        }catch (Exception e){
	            reqRes.setStatusCode(500);
	            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
	        }
	        return reqRes;

	    }

}
