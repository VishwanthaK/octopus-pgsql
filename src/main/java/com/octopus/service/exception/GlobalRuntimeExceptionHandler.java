package com.octopus.service.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import com.octopus.service.util.ExceptionResponse;


@RestControllerAdvice
public class GlobalRuntimeExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalRuntimeExceptionHandler.class);
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public ExceptionResponse handleIndexOutOfBoundsException(HttpServletRequest request, Exception ex) {
		LOGGER.error("INDEX OUT OF BOUNDS EXCEPTION OCCURED :: URL = "+request.getRequestURL());
		LOGGER.error("EXCEPTION :: "+ex);
		ex.printStackTrace();
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				"Internal server error : Index out of bound exception!!");
		return exceptionResponse;
	}
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IOException.class)
	public ExceptionResponse handleIOException(HttpServletRequest request, Exception ex){
		LOGGER.error("IO EXCEPTION OCCURED :: URL :: "+request.getRequestURL());
		LOGGER.error("EXCEPTION :: "+ex);
		ex.printStackTrace();
		ExceptionResponse exceptionResponse = new ExceptionResponse
				(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				"Internal server error : IO exception!!");
		return exceptionResponse;
	}
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public ExceptionResponse handleNullpointerException(HttpServletRequest request, Exception ex){
		LOGGER.error("NULL POINTER EXCEPTION OCCURED :: URL :: "+request.getRequestURL());
		LOGGER.error("EXCEPTION :: "+ex);
		ex.printStackTrace();
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
						"Internal server error : null pointer exception!!");
		return exceptionResponse;
	}
	
	@ResponseStatus(value=HttpStatus.EXPECTATION_FAILED)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ExceptionResponse handleJpaRepoException(HttpServletRequest request, Exception ex){
		LOGGER.error("DATA INTEGRITY VIOLATION EXCEPTION OCCURED :: URL :: "+request.getRequestURL());
		LOGGER.error("EXCEPTION :: "+ex);
		ex.printStackTrace();
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(HttpStatus.EXPECTATION_FAILED.value(),
						"Internal server error : data integrity voilation exception!!");
		return exceptionResponse;
	}

	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MultipartException.class)
    public ExceptionResponse handleMultipartException(MultipartException ex, HttpServletRequest request) {
		LOGGER.error("MULTIPART EXCEPTION :: URL = "+request.getRequestURL());
		LOGGER.error("EXCEPTION :: "+ex);
		ex.printStackTrace();
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return exceptionResponse;

	}
	
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ExceptionResponse handleUncaughtExceptions(HttpServletRequest request, Exception ex) {
		LOGGER.error("UN-CAUGHT EXCEPTIONS OCCURED :: URL = "+request.getRequestURL());
		LOGGER.error("EXCEPTION :: "+ex);
		ex.printStackTrace();
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return exceptionResponse;
	}
	

}
