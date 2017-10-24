package com.octopus.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.octopus.service.domain.JwtUser;
import com.octopus.service.domain.model.User;
import com.octopus.service.domain.repository.UserRepository;
import com.octopus.service.security.JwtTokenUtil;
import com.octopus.service.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;

	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public User getUserByToken(String token) {
		String username = null;
		User loggedInUser = null;
		username = jwtTokenUtil.getUsernameFromToken(token);
		loggedInUser = userRepository.findByUsername(username);
		return loggedInUser;
	}
	
	@Override
	public long getUserIdByToken(String token) {
		String username = null;
		User loggedInUser = null;
		username = jwtTokenUtil.getUsernameFromToken(token);
		loggedInUser = userRepository.findByUsername(username);
		return loggedInUser.getId();
	}
	
	@Override
	public JwtUser getUserDetails(String token) {
		String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
		return user;
	}

	@Override
	public String test(String value) {
		System.out.println("Given value : "+value);
//		String response = testService.rfeturnSomeString(); 
		return "DFGFGFGDF";
	}

	@Override
	public String returnSomeString() {
//		String response = testService.rfeturnSomeString(); 
		return "FGHFGH;GFH;;";
	}
}
