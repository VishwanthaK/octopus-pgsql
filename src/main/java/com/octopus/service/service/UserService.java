package com.octopus.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.JwtUser;
import com.octopus.service.domain.model.User;
import com.octopus.service.domain.model.UserAddress;


public interface UserService {
	
	User getUserByToken(String token);
	
	Long getUserIdByToken(String token);
	
	JwtUser getUserDetails(String token);
	
	String addUserAddress(String token, UserAddress addressInput) throws JsonProcessingException;
	
	String getUserAddress(String token)throws JsonProcessingException;
}
