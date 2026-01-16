package com.project.exceptions;

public class OrderItemNotFoundException extends RuntimeException{

	public OrderItemNotFoundException(String msg) {
		super(msg); 
	}
}
