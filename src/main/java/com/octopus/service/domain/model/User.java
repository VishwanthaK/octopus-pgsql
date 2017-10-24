package com.octopus.service.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.octopus.service.domain.BaseModel;

@Entity
@Table(name = "mstr_user")
@JsonFilter("USER_FILTER")
public class User extends BaseModel {
	
	private static final long serialVersionUID = 790783626359984900L;
	
	private String fullname;
	private String email;
	private String mobileNumber;
    private String username;
    private String password;
    private List<Role> authorities;
    private Boolean enabled;
   
	
	@Column(name = "fullname", length = 50)
    @NotNull
    @Size(min = 3, max = 50)
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String firstname) {
        this.fullname = firstname;
    }
    
    @Column(name = "email", length = 50)
    @Size(min = 4, max = 50)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name = "mobile_number", length = 50, unique = true)
    @NotNull
    @Size(min = 10, max = 10)
    public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
	@Column(name = "username", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    
    @Column(name = "password", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "mstr_user_role",
          joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
          inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    public List<Role> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}
	
	@Column(name = "enabled")
    @NotNull
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}