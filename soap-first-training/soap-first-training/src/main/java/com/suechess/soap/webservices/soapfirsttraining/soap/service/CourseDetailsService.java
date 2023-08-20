package com.suechess.soap.webservices.soapfirsttraining.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.suechess.soap.webservices.soapfirsttraining.soap.bean.Course;

@Component
public class CourseDetailsService {
	
	public enum Status {
		SUCCESS, FAILURE;
	}

	private static List<Course> courses = new ArrayList<Course>();
	
	static {
		Course course1 = new Course(1, "Microservices", "Master the microservices architecture");
		courses.add(course1);
		
		Course course2 = new Course(2, "Database connection", "Learn to interact with DB from the backend with JAVA");
		courses.add(course2);
		
		Course course3 = new Course(3, "Servlets", "Learn the basics of Tomcat"); 
		courses.add(course3);
	}
	
	
	public Course findById(int id) {
		for(Course course:courses) {
			if(course.getId()==id) {
				return course;
			}
		}
		return null;
	}
	
	
	public List<Course> findAll(){
		return courses;
		
	}
		
	
	public Status deleteById(int id) {
		
		Iterator<Course> iterator = courses.iterator();
		
		while (iterator.hasNext()) {
			Course course = (Course) iterator.next();
			if (course.getId()==id) {
				iterator.remove();
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}
	
}
