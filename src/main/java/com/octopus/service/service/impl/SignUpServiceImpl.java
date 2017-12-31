package com.octopus.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.octopus.service.domain.model.Role;
import com.octopus.service.domain.model.User;
import com.octopus.service.domain.model.UserRole;
import com.octopus.service.domain.repository.RoleRepo;
import com.octopus.service.domain.repository.UserRepo;
import com.octopus.service.domain.repository.UserRoleRepo;
import com.octopus.service.service.SignUpService;

@Service("userSignUpService")
public class SignUpServiceImpl implements SignUpService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	UserRoleRepo userRoleRepo;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public User createNewAccount(User newUser) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		newUser.setUsername(newUser.getMobileNumber());
		newUser.setEnabled(true);
		newUser = userRepo.saveAndFlush(newUser);
		saveUserRole(newUser);
		return newUser;
	}

	@Override
	public void saveUserRole(User createdUser) {
		UserRole userRoleMapping = new UserRole();
		Role role = roleRepo.getAuthorityByName("ROLE_USER");
		userRoleMapping.setAuthority(role);
		userRoleMapping.setUser(createdUser);
		userRoleRepo.saveAndFlush(userRoleMapping);
	}

}
