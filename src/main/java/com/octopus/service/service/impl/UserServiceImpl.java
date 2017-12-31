package com.octopus.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.JwtUser;
import com.octopus.service.domain.model.User;
import com.octopus.service.domain.model.UserAddress;
import com.octopus.service.domain.repository.UserAddressRepo;
import com.octopus.service.domain.repository.UserRepo;
import com.octopus.service.exception.OctopusPermissionException;
import com.octopus.service.response.filter.UserFilter;
import com.octopus.service.security.JwtTokenUtil;
import com.octopus.service.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;

	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserAddressRepo userAddressRepo;
	
	
	@Override
	public User getUserByToken(String token) {
		String username = null;
		User loggedInUser = null;
		username = jwtTokenUtil.getUsernameFromToken(token);
		loggedInUser = userRepo.findByUsername(username);
		return loggedInUser;
	}
	
	@Override
	public Long getUserIdByToken(String token) {
		String username = null;
		User loggedInUser = null;
		username = jwtTokenUtil.getUsernameFromToken(token);
		loggedInUser = userRepo.findByUsername(username);
		return loggedInUser.getId();
	}
	
	@Override
	public JwtUser getUserDetails(String token) {
		String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
		return user;
	}

	@Override
	public String addUserAddress(String token, UserAddress addressInput) throws JsonProcessingException{
		User user = getUserByToken(token);
		List<UserAddress> userAddress = userAddressRepo.getUserAddress(user.getId());
		if(userAddress.size() > 0)
			throw new OctopusPermissionException("You already updated the address.");
		addressInput.setUserObj(user);
		addressInput = userAddressRepo.saveAndFlush(addressInput);
		return UserFilter.filterUserAddressEntity(addressInput);
	}

	@Override
	public String getUserAddress(String token) throws JsonProcessingException {
		User user = getUserByToken(token);
		return UserFilter.filterUserAddressEntity(user.getUserAddress());
	}
}
