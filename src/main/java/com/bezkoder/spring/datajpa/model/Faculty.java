package com.bezkoder.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "faculties")
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "facultyname")
	private String facultyname;

	
	@Column(name = "subject")
	private String subject;

	@Column(name = "employee")
	private boolean employee;

	public Faculty() {

	}

	public Faculty(String facultyname, String subject, boolean employee) {
		this.facultyname = facultyname;
		this.subject = subject;
		this.employee = employee;
	}

	public long getId() {
		return id;
	}



	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject ;
	}

	public boolean isEmployee() {
		return employee;
	}

	public void setEmployee(boolean employee) {
		this.employee = employee;
	}
	
	public String getFacultyname() {
		return facultyname;
	}

	public void setFacultyname(String facultyname) {
		this.facultyname = facultyname;
	}


	@Override
	public String toString() {
		return "Faculty [id=" + id + ", facultyname=" + facultyname + ", subject=" + subject + ", employe=" + employee + "]";
	}

}
