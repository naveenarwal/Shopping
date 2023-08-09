package com.shs.app.exceptions;

import org.springframework.stereotype.Service;

@Service
public class DataNotFoundException extends RuntimeException {

	private DataNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public DataNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
