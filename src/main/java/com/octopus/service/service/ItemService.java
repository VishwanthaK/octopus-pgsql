package com.octopus.service.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.ApiResponse;
import com.octopus.service.domain.model.Item;

public interface ItemService {
	
	String getItem(String token, Long itemtypeId, Pageable pageable) throws JsonProcessingException;

	ApiResponse addItem(String token, Item item);
	
	void updateItem(String token, Item item);

	ApiResponse uploadImage(String token, Long itemId, MultipartFile image);
}
