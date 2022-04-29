package com.bezkoder.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "course")
	private String course;

	@Column(name = "stream")
	private boolean stream;

	public Student() {

	}

	public Student(String name, String course, boolean stream) {
		this.name = name;
		this.course = course;
		this.stream = stream;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public boolean isStream() {
		return stream;
	}

	public void setStream(boolean isStream) {
		this.stream = isStream;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", course=" + course + ", stream=" + stream + "]";
	}

}
