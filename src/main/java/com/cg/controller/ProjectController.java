package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Entity.UserEntity;
import com.cg.dto.AuthRequest;
import com.cg.dto.ResponseDto;
import com.cg.entity.UserCredential;
import com.cg.exception.CustomException;
import com.cg.repository.UserCredentialRepository;
import com.cg.repository.UserRepository;
import com.cg.service.UserService;
import com.cg.service1.AuthService;

@RestController
@RequestMapping("/auth")
public class ProjectController {
	
	@Autowired
	private UserService userSer;
	
	@Autowired
	private AuthService authSer;
	
	@Autowired
     private UserRepository userRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/users/{email}")
	public UserEntity getUserByEmail(@PathVariable String email) {
		return userSer.getUserProfile(email);
	}
	
	@PutMapping("/update/user/{userId}")
	public ResponseEntity<UserEntity> updateUserProfile(@PathVariable UserEntity user, String userId) throws CustomException {
		UserEntity userEntity = userSer.updateUserProfile(user, userId);
		ResponseEntity<UserEntity> rs = new ResponseEntity<UserEntity>(userEntity, HttpStatus.OK);
		return rs;
	}
	
	 @PostMapping("/register")
	    public String addNewUser(@RequestBody UserEntity user) {
	        return authSer.saveUser(user);
	    }

	    @PostMapping("/login")
	    public ResponseDto getToken(@RequestBody AuthRequest authRequest) {
	    	System.out.println("yes .."+authRequest.getUsername()+"  "+authRequest.getPassword());
	        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        System.out.println(authenticate.isAuthenticated());
	        if (authenticate.isAuthenticated()) {
	        	String token=
	        	authSer.generateToken(authRequest.getUsername());
	        	UserEntity user=
	        	userRepo.findByUsername(authRequest.getUsername()).get();
	        	ResponseDto resDto=new ResponseDto();
	        	resDto.setToken(token);
	        	//resDto.setRole(user.getRole());
	        	return resDto;
	        } else {
	            throw new RuntimeException("invalid access");
	        }
	    }

	    @GetMapping("/validate")
	    public String validateToken(@RequestParam("token") String token) {
	        authSer.validateToken(token);
	        return "Token is valid";
	    }
	
	

}
