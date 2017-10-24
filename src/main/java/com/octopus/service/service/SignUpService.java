package com.octopus.service.service;

import com.octopus.service.domain.model.User;

public interface SignUpService {
	
	public void createNewAccount(User newUser);
	
	public void saveUserRole(User createdUser);

}
