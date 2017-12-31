package com.octopus.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.model.Item;
import com.octopus.service.domain.repository.ItemRepo;
import com.octopus.service.response.filter.ItemFilter;
import com.octopus.service.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Override
	public void addItem(String token, Item item) {
		itemRepo.saveAndFlush(item);
	}

	@Override
	public void updateItem(String token, Item item) {
		itemRepo.getOne(item.getId()); //to validate given id is valid or not. throw 500 error code exception. 
		itemRepo.saveAndFlush(item);
	}

	@Override
	public String getItem(String token, Long itemtypeId, Pageable pageable) 
			throws JsonProcessingException {
		List<Item> items = itemRepo.getItembyType(itemtypeId, pageable);
 		return ItemFilter.filterItemEntityList(items);
	}

}
