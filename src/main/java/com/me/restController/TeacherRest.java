package com.me.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.Service.TeacherService;
import com.me.Service.TeacherServiceImpl;
import com.me.entity.Teacher;

@RestController
public class TeacherRest
{
	@Autowired
	TeacherService teacherService;
	
	@PostMapping("/createTeacher")
	public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher)
	{
		Teacher teachers = teacherService.createTeachers(teacher);
		
		return new ResponseEntity<Teacher > (teachers,HttpStatus.ACCEPTED);
	}
}
	
	
	
	
	
	
	
	
	
	