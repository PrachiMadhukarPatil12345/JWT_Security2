package com.me.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.Security.Security;
import com.me.Service.CustomTeacherService;
// import com.veracity.rest.AuthRequest;
import com.me.Service.JWTUtil;

@RestController
public class AuthRestController
{
//	private  AuthenticationManager authManager;
//	private  JWTUtil jwtutil;
//	private  UserDetailsService userDetailsService;
//
//
//
//	public void AuthController(AuthenticationManager authManager, JWTUtil jwtutil, UserDetailsService userDetailsService) 
//	{
//		this.authManager = authManager;
//		this.jwtutil = jwtutil;
//		this.userDetailsService = userDetailsService;
//	}
	
	

	@Autowired
	private  AuthenticationManager authManager;
	
	@Autowired
	private CustomTeacherService customTeacherService;
	
	@Autowired
	private JWTUtil jwtutil;
	
	
	
	
	@PostMapping("/login")
	public String login(@RequestBody AuthRequest request)
	{
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		UserDetails user = customTeacherService.loadUserByUsername(request.getUsername());
		
		return jwtutil.generateToken(user.getUsername());
	}
	
	
}

class AuthRequest
{
	private String username;
	private String password;
	
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}

	// getters and setters






