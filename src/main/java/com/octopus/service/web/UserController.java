package com.octopus.service.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.model.UserAddress;
import com.octopus.service.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Value("${token.header}")
    private String tokenHeader;
	
	@Autowired
    private UserService userService;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/address", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUserAddress(HttpServletRequest request, 
    		@RequestBody UserAddress addressInput) throws JsonProcessingException {
		String token = request.getHeader(tokenHeader);
		String address = userService.addUserAddress(token, addressInput);
        return new ResponseEntity<String>(address, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')") 
	//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value = "/address", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserAddress(HttpServletRequest request) 
    		throws JsonProcessingException {
		String token = request.getHeader(tokenHeader);
		String address = userService.getUserAddress(token);
        return new ResponseEntity<String>(address, HttpStatus.OK);
	}

}
