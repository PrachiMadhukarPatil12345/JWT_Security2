package com.me.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Teacher 
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

    
    @Column(name = "first_name", nullable = false, length = 50)
    
    private String firstName;
    

    @Column(name = "last_name", nullable = false, length = 50)
    
    private String lastName;
    
    
   
    private String password;
    
    private String role;

    @Column(nullable = false, unique = true, length = 100)
    
    private String email;

    @Column(name = "subject_specialization")
    
    private String subject;

    @Column(name = "hire_date")
    
    private LocalDate hireDate;
}
