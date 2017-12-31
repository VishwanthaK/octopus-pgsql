package com.octopus.service.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.octopus.service.domain.BaseModel;

@Entity
@Table(name = "mstr_item_type")
@JsonFilter("ITEM_TYPE_FILTER")
public class ItemType extends BaseModel {

	private static final long serialVersionUID = 565766160246394415L;
	
	private String typeName;

	@Column(name = "type_name")
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
