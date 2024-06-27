package com.example.test.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1639831116670513657L;
	
	private HttpStatus httpStatus;
	private String body;
	
	
	public ApiException(HttpStatus httpStatus, String body) {
		super();
		this.httpStatus = httpStatus;
		this.body = body;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}


	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}	

}
