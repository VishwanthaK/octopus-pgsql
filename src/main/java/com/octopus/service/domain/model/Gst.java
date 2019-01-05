package com.octopus.service.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.octopus.service.domain.BaseModel;

@Entity
@Table(name = "mstr_gst")
@JsonFilter("GST_FILTER")
public class Gst extends BaseModel {
	
	private static final long serialVersionUID = 304161930492071317L;
	
	private Double gstValue;

	@Column(name = "gst_value")
	public Double getGstValue() {
		return gstValue;
	}
	public void setGstValue(Double gstValue) {
		this.gstValue = gstValue;
	}
}
