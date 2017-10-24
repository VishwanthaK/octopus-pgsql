package com.octopus.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.octopus.service.domain.SuccessResponse;
import com.octopus.service.domain.model.User;
import com.octopus.service.service.SignUpService;
import com.octopus.service.util.AppResponse;
import com.octopus.service.util.AppUtil;


@RestController
@RequestMapping("/register")
public class SignUpController {
	
	private SuccessResponse response =null;
	
	@Autowired
	SignUpService signUpService;
	
	@RequestMapping(value = "/new/account", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse> createUser(@RequestBody User newUser) {
		signUpService.createNewAccount(newUser);
		response = AppUtil.frameSuccessResponse(
				HttpStatus.OK.value(), 
				AppResponse.SUCCESSFUL_SIGNUP);
        return new ResponseEntity<SuccessResponse>(response, HttpStatus.OK);
	}
}
