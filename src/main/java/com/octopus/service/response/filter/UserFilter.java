package com.octopus.service.response.filter;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.octopus.service.domain.model.User;
import com.octopus.service.domain.model.UserAddress;

public class UserFilter {
	
	private UserFilter(){/*hiding this class*/}
	
	private static String[] USER_IGNORABLE_FLDS = { 
			"createdOn", "username", "password","authorities", "enabled", "userAddress"};
	private static String[] ADDRESS_IGNORABLE_FLDS = {"createdOn"};
	
 
	
	public static String filterUserEntity(User userEntity) throws JsonProcessingException {   
		ObjectWriter writer = null;
		String responseJson = null;
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()  
		    .addFilter("USER_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(USER_IGNORABLE_FLDS));
		writer = mapper.writer(filters);
		responseJson = writer.writeValueAsString(userEntity);
		return responseJson;
    }
	
	public static String filterUserAddressEntity(List<UserAddress> userAddressEntity) 
			throws JsonProcessingException {   
		ObjectWriter writer = null;
		String responseJson = null;
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()  
		    .addFilter("USER_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(USER_IGNORABLE_FLDS))
		    .addFilter("USER_ADDRESS_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(ADDRESS_IGNORABLE_FLDS));
		writer = mapper.writer(filters);
		responseJson = writer.writeValueAsString(userAddressEntity);
		return responseJson;
    }
	
	public static String filterUserAddressEntity(UserAddress userAddressEntity) 
			throws JsonProcessingException {   
		ObjectWriter writer = null;
		String responseJson = null;
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()  
		    .addFilter("USER_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(USER_IGNORABLE_FLDS))
		    .addFilter("USER_ADDRESS_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(ADDRESS_IGNORABLE_FLDS));
		writer = mapper.writer(filters);
		responseJson = writer.writeValueAsString(userAddressEntity);
		return responseJson;
    }
}
