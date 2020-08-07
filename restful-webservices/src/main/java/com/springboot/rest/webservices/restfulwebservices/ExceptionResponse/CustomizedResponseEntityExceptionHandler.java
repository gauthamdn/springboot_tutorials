package com.springboot.rest.webservices.restfulwebservices.ExceptionResponse;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.rest.webservices.restfulwebservices.user.UserNotFoundException;


// controller advice helps to share this class or exception handler across the multiple controllers ( eg: helloworld controller, user resource controller
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	// override the handleExceptionmethod and customize it
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(),ex.getMessage(),
						request.getDescription(false));

		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(),ex.getMessage(),
						request.getDescription(false));

		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
		
	}

	// to handle validation failures - overide the below method from ResponseEntityExceptioHandler
	@Override	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		// store the binding result which contains the validation error as description to the custom exceptionresponse object
		ExceptionResponse exceptionResponse =
				new ExceptionResponse(new Date(),"Validation Failed",
						ex.getBindingResult().toString());
		
		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}

	
}
