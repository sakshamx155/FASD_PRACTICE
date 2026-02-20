package com.klu.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CourseRegistration {
	@Value("101")
	private int rollno;
	@Value("Akshaya")
	private String studentname;
	
	private String coursename;
	
	private int semester;
	
	public CourseRegistration(@Value("FSAD") String coursename) {
		this.coursename=coursename;
	}
	@Value("4")
	public void getSemester(int semester) {
		this.semester=semester;
	}
	public void display() {
		System.out.println("Rollno:"+rollno);
		System.out.println("Name:"+studentname);
		System.out.println("Course:"+coursename);
		System.out.println("Semeter:"+semester);
	}

}
