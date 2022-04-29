package com.bezkoder.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "login")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "username")
	private String username;

	
	@Column(name = "password")
	private String password;

	@Column(name = "employee")
	private boolean employee;

	public Login() {

	}

	public Login(String username, String password, boolean employee) {
		this.username = username;
		this.password = password;
		this.employee = employee;
	}

	public long getId() {
		return id;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username ;
	}

	public boolean isEmployee() {
		return employee;
	}

	public void setEmployee(boolean employee) {
		this.employee = employee;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Login [id=" + id + ", username=" + username + ", password=" + password + ", employee=" + employee + "]";
	}

}
