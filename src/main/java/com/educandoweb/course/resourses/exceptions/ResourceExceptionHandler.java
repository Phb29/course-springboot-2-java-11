package com.educandoweb.course.resourses.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandError> resourcenotfound(ResourceNotFoundException e,HttpServletRequest request){
		String error="Resorce not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandError err= new StandError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
@ExceptionHandler(DatabaseException.class)
public ResponseEntity<StandError> database(DatabaseException e,HttpServletRequest request){
	String error="DataBase error";
	HttpStatus status = HttpStatus.BAD_REQUEST;
	StandError err= new StandError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
	return ResponseEntity.status(status).body(err);
}

}
