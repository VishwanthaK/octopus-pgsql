package com.octopus.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MasterService {
	
	String getActiveGstSlabs(String token) throws JsonProcessingException;
	
	String getActiveItemTypes(String token) throws JsonProcessingException;

}
