package com.me.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.me.Service.CustomTeacherService;
import com.me.Service.JWTFilter;

@Configuration
@EnableWebSecurity

public class Security
{
	
	@Bean
	public BCryptPasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	CustomTeacherService customTeacherService;
	
	@Autowired
	JWTFilter jwtfilter;
	
	
	
	public AuthenticationProvider provider()
	{
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider( customTeacherService );
		
		auth.setPasswordEncoder(encoder () );
		
		return auth;
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration auth)
	{
		return auth.getAuthenticationManager();
	}
	
	
	
	
	@Bean
	public SecurityFilterChain managesecurity(HttpSecurity http) throws Exception
	{
		http.csrf(csrf->csrf.disable() );
		
		http.authorizeHttpRequests(auth ->
		{
			auth
			.requestMatchers("/createTeacher").permitAll()
			
			.requestMatchers("/welcome").permitAll()
			
			.requestMatchers("/login").permitAll()
			
			.requestMatchers("/welcome2").hasRole("ADMIN")
			
			.requestMatchers("/user").hasRole("USER")
			
			.requestMatchers("/admin").hasAnyRole("USER","ADMIN")
			
			.anyRequest().authenticated();
			
		} ).addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
		
		.httpBasic(Customizer.withDefaults() );
		
		
		return http.build();
		
	}

}
