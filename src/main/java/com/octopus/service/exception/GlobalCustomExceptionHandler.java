package com.octopus.service.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.octopus.service.util.ExceptionConstants;
import com.octopus.service.util.ExceptionResponse;


@RestControllerAdvice
public class GlobalCustomExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalCustomExceptionHandler.class);
	
	
	@ResponseStatus(value=HttpStatus.EXPECTATION_FAILED)
	@ExceptionHandler(OctopusPermissionException.class)
	public ExceptionResponse handleOctopusPermissionException(HttpServletRequest request, Exception ex) {
		LOGGER.error("OCTOPUS PERMISSION EXCEPTION OCCURED :: URL = "+request.getRequestURL());
		LOGGER.error("EXCEPTION :: "+ex);
		ex.printStackTrace();
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				ExceptionConstants.OCTOPUS_PERMISSION_EXCEPTION_CODE, 
				ex.getMessage());
		return exceptionResponse;
	}
	
}
