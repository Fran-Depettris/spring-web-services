package com.suechess.soap.webservices.soapfirsttraining.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://suechess.com/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 6548648362476820553L;

	public CourseNotFoundException(String message) {
		super(message);
	}
	
	
}
