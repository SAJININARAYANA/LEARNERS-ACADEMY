package com.bezkoder.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "time")
	private String time;
	
	@Column(name = "clsbreak")
	private boolean clsbreak;
	

	public Subject() {

	}

	public Subject(String name, String time,Boolean clsbreak) {
		this.name = name;
		this.time = time;
		this.clsbreak = clsbreak;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	public boolean isClsBreak() {
		return clsbreak;
	}

	public void setClsBreak(boolean isClsBreak) {
		this.clsbreak = isClsBreak;
	}
	

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", time=" + time + ", clsbreak=" + clsbreak + "]";
	}

}
