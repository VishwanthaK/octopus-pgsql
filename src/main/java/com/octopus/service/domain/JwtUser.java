package com.octopus.service.domain;

import java.util.Collection;

import org.joda.time.LocalDateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails {

    
	private static final long serialVersionUID = 6572114245032333694L;
	
	private final Long id;
    private final String fullname;
    private final String email;
    private final String mobileNumber;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final LocalDateTime createdOn;

    public JwtUser(
          Long id,
          String fullname,
          String email,
          String mobileNumber,
          String username,
          String password, 
          Collection<? extends GrantedAuthority> authorities,
          boolean enabled,
          LocalDateTime createdOn) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.createdOn = createdOn;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }
    public String getMobileNumber() {
		return mobileNumber;
	}

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
}
