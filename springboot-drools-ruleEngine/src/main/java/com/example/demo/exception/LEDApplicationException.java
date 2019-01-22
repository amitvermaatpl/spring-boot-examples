package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind .annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LEDApplicationException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4204124463715433691L;
	
	private final String error; 
	private final String message;
	
	
	public   LEDApplicationException(String message, String error){
		super(message);
		this.error = error;
		this.message = message;
	}


	public String getMessage() {
		return message;
	}
}
