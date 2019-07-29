package com.octopus.service.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.octopus.service.domain.model.Role;
import com.octopus.service.domain.model.UserRole;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
    private final Long user_id;
    private final List<String> user_roles;

    public JwtAuthenticationResponse(String token, Long user_id, List<UserRole> userRoles) {
        this.token = token;
        this.user_id = user_id;
        List<Role> roles = userRoles.stream().map(UserRole::getAuthority).collect(Collectors.toList());
        user_roles = roles.stream().map(Role::getName).collect(Collectors.toList());
    }
    public String getToken() {
        return this.token;
    }
	public Long getUser_id() {
		return user_id;
	}
    public List<String> getUser_roles() { return user_roles; }
}
