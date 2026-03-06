package com.me.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestController
{
	@GetMapping("/welcome")
	public String welcome()
	{
		return "WElcomE wE arE LearninG Jwt SecuritY";
	}
	
	@GetMapping("/welcome2")
	public String welcome2()
	{
		return "WElcomE wE arE LearninG Jwt SecuritY   222222";
	}
	
	@GetMapping("/user")
	public String user()
	{
		return "welcome user";
	}
	
	@GetMapping("/admin")
	public String admin()
	{
		return "welcome admin";
	}
	
	
	
	
}
