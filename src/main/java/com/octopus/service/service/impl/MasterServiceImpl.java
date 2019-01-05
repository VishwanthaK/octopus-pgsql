package com.octopus.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.model.Gst;
import com.octopus.service.domain.model.ItemType;
import com.octopus.service.domain.repository.GstRepository;
import com.octopus.service.domain.repository.ItemTypeRepository;
import com.octopus.service.response.filter.ItemFilter;
import com.octopus.service.service.MasterService;
import com.octopus.service.util.AppConstants;

@Service
public class MasterServiceImpl implements MasterService {

	
	@Autowired
	private ItemTypeRepository itemTypeRepository;
	
	@Autowired
	private GstRepository gstRepository;
	
	@Override
	public String getActiveGstSlabs(String token) throws JsonProcessingException {
		List<Gst> gstSlabs = gstRepository.getGstSlabsByStatus(AppConstants.ACTIVE_RECORD_STATUS);
		return ItemFilter.filterGstSlabsEntityList(gstSlabs);
	}

	@Override
	public String getActiveItemTypes(String token) throws JsonProcessingException {
		List<ItemType> itemTypes = itemTypeRepository.getItemTypesByStatus(AppConstants.ACTIVE_RECORD_STATUS);
		return ItemFilter.filterItemTypeEntityList(itemTypes);
	}

	

}
