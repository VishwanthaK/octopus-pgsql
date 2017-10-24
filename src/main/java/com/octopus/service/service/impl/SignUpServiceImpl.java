package com.octopus.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.octopus.service.domain.model.Role;
import com.octopus.service.domain.model.User;
import com.octopus.service.domain.model.UserRole;
import com.octopus.service.domain.repository.RoleRepository;
import com.octopus.service.domain.repository.UserRepository;
import com.octopus.service.domain.repository.UserRoleRepository;
import com.octopus.service.service.SignUpService;

@Service("userSignUpService")
public class SignUpServiceImpl implements SignUpService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public void createNewAccount(User newUser) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		newUser.setUsername(newUser.getMobileNumber());
		newUser.setEnabled(true);
		newUser = userRepository.saveAndFlush(newUser);
		saveUserRole(newUser);
	}

	@Override
	public void saveUserRole(User createdUser) {
		UserRole userRoleMapping = new UserRole();
		Role role = roleRepository.getAuthorityByName("ROLE_USER");
		userRoleMapping.setAuthority(role);
		userRoleMapping.setUser(createdUser);
		userRoleRepository.saveAndFlush(userRoleMapping);
	}

}
