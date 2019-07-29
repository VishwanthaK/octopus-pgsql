package com.octopus.service.web;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.octopus.service.domain.JwtAuthenticationRequest;
import com.octopus.service.domain.JwtAuthenticationResponse;
import com.octopus.service.domain.JwtUser;
import com.octopus.service.domain.model.User;
import com.octopus.service.security.JwtTokenUtil;
import com.octopus.service.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
    @Value("${token.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        //role based changes
        List<GrantedAuthority> updatedAuthorities = new ArrayList<GrantedAuthority>();
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
        	updatedAuthorities.add(grantedAuthority);
		}
        final Authentication updatedAuth = authenticationManager
        		.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword(), updatedAuthorities));
        SecurityContextHolder.getContext().setAuthentication(updatedAuth);
        //changes ends here
        //old... below one line code
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);
        final User user = userService.getUserByToken(token);

        return ResponseEntity.ok(new JwtAuthenticationResponse(token, user.getId(), user.getUserRoles()));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getCreatedOn())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            final User userByToken = userService.getUserByToken(refreshedToken);
            return ResponseEntity.ok(new JwtAuthenticationResponse(token, userByToken.getId(), userByToken.getUserRoles()));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
