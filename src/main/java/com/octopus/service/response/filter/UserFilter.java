package com.octopus.service.response.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.octopus.service.domain.model.User;

public class UserFilter {
	
	private UserFilter() {
		//hiding this class
	}
	
	private static String[] userIgnorableFlds = { 
			"updatedOn", "username", "password", 
			"registeredOn", "authorities", "enabled" };
	
 
	
	public static String filterItemList(User userObj) throws JsonProcessingException {   
		ObjectWriter writer=null;
		String responseJson = null;
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()  
		    .addFilter("USER_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(userIgnorableFlds));
		writer = mapper.writer(filters);
		responseJson = writer.writeValueAsString(userObj);
		return responseJson;
    }
}
