package com.shs.app.errorresponse;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shs.app.exceptions.AlreadyFoundDataException;
import com.shs.app.exceptions.DataNotFoundException;

@RestControllerAdvice
public class MyErrorResponseClass {

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ErrorResponse> notFoundDataException(DataNotFoundException msg){
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(500,msg.getMessage(),new Date().toString(),"Error While Processing Data !!")
				,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(AlreadyFoundDataException.class)
	public ResponseEntity<ErrorResponse> alreadyDataExistException(AlreadyFoundDataException msg){
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(500,msg.getMessage(),new Date().toString(),"Error While Processing Data !!")
				,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
