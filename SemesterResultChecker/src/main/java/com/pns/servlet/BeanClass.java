package com.pns.servlet;

import java.io.Serializable;

public class BeanClass implements Serializable{
	private long rollno ;
	private int slno;
	private String name,mail;
	private float sgpa, cgpa;
	private String[] grades = new String[8];
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getRollno() {
		return rollno;
	}
	public void setRollno(long rollno) {
		this.rollno = rollno;
	}
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	public String[] getGrades() {
		return grades;
	}
	public void setGrades(String[] grades) {
		this.grades = grades;
	}
	public float getSgpa() {
		return sgpa;
	}
	public void setSgpa(float sgpa) {
		this.sgpa = sgpa;
	}
	public float getCgpa() {
		return cgpa;
	}
	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}
	public BeanClass() {
	}
	public void setMail(String mail) {
		this.mail = mail;		
	}
	public String getMail() {
		return mail;
	}
		
}
