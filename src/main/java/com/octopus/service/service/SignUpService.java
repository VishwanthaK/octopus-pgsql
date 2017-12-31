package com.octopus.service.service;

import com.octopus.service.domain.model.User;

public interface SignUpService {
	
	User createNewAccount(User newUser);
	
	void saveUserRole(User createdUser);

}
