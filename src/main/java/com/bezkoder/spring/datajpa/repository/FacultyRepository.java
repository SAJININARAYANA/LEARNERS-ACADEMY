package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Faculty;
import com.bezkoder.spring.datajpa.model.Student;
import com.bezkoder.spring.datajpa.model.Tutorial;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
	List<Faculty> findByEmployee(boolean employee);
	List<Faculty> findByFacultynameContaining(String facultyname);
	
}
