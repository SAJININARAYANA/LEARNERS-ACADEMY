package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.Login;



public interface LoginRepository extends JpaRepository<Login, Long> {
	//List<Login> findByPublished(boolean published);
	List<Login> findByUsernameContaining(String username);
}