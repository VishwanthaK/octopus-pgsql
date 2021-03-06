package com.octopus.service.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.octopus.service.domain.BaseModel;

@Entity
@Table(name = "mstr_role")
public class Role extends BaseModel {

	private static final long serialVersionUID = 6892046008538847541L;
	
    private String name;
    
    @Column(name = "name", length = 50)
    @NotNull
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}