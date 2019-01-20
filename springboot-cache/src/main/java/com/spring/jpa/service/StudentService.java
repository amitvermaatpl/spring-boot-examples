package com.spring.jpa.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.spring.jpa.domain.Student;

@Service
public class StudentService {

	@Cacheable("student-by-id")
	public Student getStudentById(String id){
		try{
			System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
			Thread.sleep(1000*5);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		return new Student(Integer.valueOf(id),"Amit", "Java");
	}
	
	// @CacheEvict(allEntries=true) clears all the @Cacheable-entries from the cache. //
	@CacheEvict(value = "student-by-id")
	public Student updateStudent(String id){
		return new Student(Integer.valueOf(id),"Amit Verma", "Java");
	}
	
}
