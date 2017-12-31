package com.octopus.service.service;

import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.model.Item;

public interface ItemService {
	
	String getItem(String token, Long itemtypeId, Pageable pageable) throws JsonProcessingException;
	
	void addItem(String token, Item item);
	
	void updateItem(String token, Item item);
}
