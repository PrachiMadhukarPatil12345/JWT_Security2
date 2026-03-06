package com.me.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.me.entity.Teacher;
import com.me.repo.TeacherRepository;

@Service

public class CustomTeacherService implements UserDetailsService  // Core interface which loads user-specific data.

                                          // It is used throughout the framework as a user DAO and is the strategy
                                       // used by the DaoAuthenticationProvider.

{
	@Autowired
	TeacherRepository teacherRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Optional<Teacher> byFirstname = teacherRepository.findByFirstName(username);
		
		if(byFirstname.isPresent() )
		{
			 Teacher teacher = byFirstname.get();
			 
			 return 
					 org.springframework.security.core.userdetails.User.
					 withUsername(teacher.getFirstName())
					 .password(teacher.getPassword() )
					 .roles(teacher.getRole() )
					 .build();
					
			 
		}
		
			throw new  UsernameNotFoundException("Not Found !!");
	
		
		
	}

}
