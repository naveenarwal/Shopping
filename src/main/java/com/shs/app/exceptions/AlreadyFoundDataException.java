package com.shs.app.exceptions;

public class AlreadyFoundDataException extends RuntimeException{

	private AlreadyFoundDataException() {
		// TODO Auto-generated constructor stub
	}

	private AlreadyFoundDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	private AlreadyFoundDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AlreadyFoundDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	private AlreadyFoundDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
