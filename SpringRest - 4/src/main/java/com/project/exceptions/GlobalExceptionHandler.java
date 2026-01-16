package com.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandler {

	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(OrderItemNotFoundException.class)
	public ResponseEntity<String> handleOrderItemNotFoundException(
	        OrderItemNotFoundException ex) {

	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
}

// To handle our status codes we have the response entities 
// ResponseEntity - To return the custom status codes 
// This can also return the headers 
// To return actual body also 
// ResponseEntity carry all of them 
// 404 stands for not found - ResponseEntity.notFound();
// ok will accept the body and not found not aceept the body 
// ResponseEntity.status (HttpStatusCode.notFound