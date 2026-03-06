package com.me.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.me.entity.Teacher;
import com.me.repo.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService
{
	@Autowired
	TeacherRepository teacherRepository;

	@Autowired
	BCryptPasswordEncoder encoder;
	
	
	@Override
	public Teacher createTeachers(Teacher teacher)
	{
		
		teacher.setPassword(encoder.encode(teacher.getPassword() ) );
		
		
		if(teacher.getFirstName()!=null &&  teacher.getFirstName().startsWith("v"))
		{
			teacher.setRole("ADMIN");
		}
		else
		{
			teacher.setRole("USER");
		}
		
		
		
		teacherRepository.save(teacher);
		
		return teacher;
	}

}
