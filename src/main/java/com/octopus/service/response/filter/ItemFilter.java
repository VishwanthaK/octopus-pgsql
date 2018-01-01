package com.octopus.service.response.filter;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.octopus.service.domain.model.Gst;
import com.octopus.service.domain.model.Item;
import com.octopus.service.domain.model.ItemType;
import com.octopus.service.domain.model.UserAddress;

public class ItemFilter {
	
	private ItemFilter(){/*hiding this class*/}
	private static String[] ITEM_IGNORABLE_FLDS = {"recordStatus","createdOn"};
	private static String[] ITEM_TYPE_IGNORABLE_FLDS = {"recordStatus","createdOn"};
	private static String[] GST_IGNORABLE_FLDS = {"recordStatus","createdOn"};
	
	public static String filterItemEntity(Item item) 
			throws JsonProcessingException {   
		ObjectWriter writer = null;
		String responseJson = null;
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider() 
			.addFilter("ITEM_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(ITEM_IGNORABLE_FLDS))
		    .addFilter("ITEM_TYPE_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(ITEM_TYPE_IGNORABLE_FLDS))
		    .addFilter("GST_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(GST_IGNORABLE_FLDS));
		writer = mapper.writer(filters);
		responseJson = writer.writeValueAsString(item);
		return responseJson;
    }
	
	public static String filterItemEntityList(List<Item> items) 
			throws JsonProcessingException {   
		ObjectWriter writer = null;
		String responseJson = null;
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()  
			.addFilter("ITEM_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(ITEM_IGNORABLE_FLDS))
			.addFilter("ITEM_TYPE_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(ITEM_TYPE_IGNORABLE_FLDS))
		    .addFilter("GST_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(GST_IGNORABLE_FLDS));
		writer = mapper.writer(filters);
		responseJson = writer.writeValueAsString(items);
		return responseJson;
    }
	
	public static String filterItemTypeEntityList(List<ItemType> itemTypes) 
			throws JsonProcessingException {   
		ObjectWriter writer = null;
		String responseJson = null;
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()  
			.addFilter("ITEM_TYPE_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(ITEM_TYPE_IGNORABLE_FLDS));
		writer = mapper.writer(filters);
		responseJson = writer.writeValueAsString(itemTypes);
		return responseJson;
    }

	
	public static String filterGstSlabsEntityList(List<Gst> gstSlabs) 
			throws JsonProcessingException {   
		ObjectWriter writer = null;
		String responseJson = null;
		ObjectMapper mapper = new ObjectMapper();
		FilterProvider filters = new SimpleFilterProvider()  
				.addFilter("GST_FILTER",SimpleBeanPropertyFilter.serializeAllExcept(GST_IGNORABLE_FLDS));
		writer = mapper.writer(filters);
		responseJson = writer.writeValueAsString(gstSlabs);
		return responseJson;
    }
}
