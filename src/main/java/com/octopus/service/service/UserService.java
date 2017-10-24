package com.octopus.service.service;

import com.octopus.service.domain.JwtUser;
import com.octopus.service.domain.model.User;


public interface UserService {
	
	User getUserByToken(String token);
	
	long getUserIdByToken(String token);
	
	JwtUser getUserDetails(String token);
	
	String test(String value);
	
	String returnSomeString();
	
}
