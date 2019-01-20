package com.spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.jpa.domain.Student;
import com.spring.jpa.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired 
	private StudentService studentService;
	
	@RequestMapping(method= RequestMethod.GET, value="/student/{id}")
	public Student getStudentById(@PathVariable String 	id){
		return studentService.getStudentById(id);
	}
	
	@PutMapping("/student/{id}")
	public Student updateStudent(@PathVariable String id){
		return studentService.updateStudent(id);
	}
}
