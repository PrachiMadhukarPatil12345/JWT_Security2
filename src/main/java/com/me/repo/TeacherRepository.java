package com.me.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.entity.Teacher;



public interface TeacherRepository extends JpaRepository<Teacher, Long>
{
	Optional<Teacher> findByFirstName(String first_name);
	
	
}