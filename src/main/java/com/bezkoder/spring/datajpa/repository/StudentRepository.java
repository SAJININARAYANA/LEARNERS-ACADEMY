package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Student;
import com.bezkoder.spring.datajpa.model.Tutorial;

public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByStream(boolean stream);
	List<Student> findByNameContaining(String name);
	
}
