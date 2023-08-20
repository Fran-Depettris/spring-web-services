package com.suechess.soap.webservices.soapfirsttraining.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.suechess.courses.CourseDetails;
import com.suechess.courses.DeleteCourseRequest;
import com.suechess.courses.DeleteCourseResponse;
import com.suechess.courses.GetAllCourseDetailsRequest;
import com.suechess.courses.GetAllCourseDetailsResponse;
import com.suechess.courses.GetCourseDetailsRequest;
import com.suechess.courses.GetCourseDetailsResponse;
import com.suechess.soap.webservices.soapfirsttraining.soap.bean.Course;
import com.suechess.soap.webservices.soapfirsttraining.soap.exception.CourseNotFoundException;
import com.suechess.soap.webservices.soapfirsttraining.soap.service.CourseDetailsService;
import com.suechess.soap.webservices.soapfirsttraining.soap.service.CourseDetailsService.Status;

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService service;

	
	@PayloadRoot(namespace="http://suechess.com/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processRequest (@RequestPayload GetCourseDetailsRequest request) {
		
		Course course = service.findById(request.getId());
		
		if (course == null) {
			throw new CourseNotFoundException("Invalid course ID: " + request.getId());
		}
		
		return mapCourse(course);
	}
	
	
	
	@PayloadRoot(namespace="http://suechess.com/courses", localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		List<Course> courses = service.findAll();
		
		return mapAllCourses(courses);
	}
	
	
	@PayloadRoot(namespace="http://suechess.com/courses", localPart="DeleteCourseRequest")
	@ResponsePayload
	public DeleteCourseResponse processDelete (@RequestPayload DeleteCourseRequest request) {
		DeleteCourseResponse response = new DeleteCourseResponse();
		
		Status status = service.deleteById(request.getId());
		response.setStatus(mapStatus(status));
		
		return response;
	}
	
	private com.suechess.courses.Status mapStatus(Status status) {
		if (status == Status.FAILURE) {
			return com.suechess.courses.Status.FAILURE;
		}
		return com.suechess.courses.Status.SUCCESS;
		
	}
	
	private GetAllCourseDetailsResponse mapAllCourses(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		
		for (Course course:courses) {
			response.getCourseDetails().add(mapCourseDetails(course));
		}
		return response;
	}
	
	
	
	private GetCourseDetailsResponse mapCourse(Course course) {
		
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		
		response.setCourseDetails(mapCourseDetails(course));
		
		return response;
	}
	
	
	
	private CourseDetails mapCourseDetails(Course course) {
			CourseDetails details = new CourseDetails();
			
			details.setId(course.getId());
			details.setName(course.getName());
			details.setDescription(course.getDescription());
			
			
			return details;
		}
	
}
