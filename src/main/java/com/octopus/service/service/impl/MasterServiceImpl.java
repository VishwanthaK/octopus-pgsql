package com.octopus.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.model.Gst;
import com.octopus.service.domain.model.ItemType;
import com.octopus.service.domain.repository.GstRepo;
import com.octopus.service.domain.repository.ItemTypeRepo;
import com.octopus.service.response.filter.ItemFilter;
import com.octopus.service.service.MasterService;
import com.octopus.service.util.AppConstants;

@Service
public class MasterServiceImpl implements MasterService {

	
	@Autowired
	private ItemTypeRepo itemTypeRepo;
	
	@Autowired
	private GstRepo gstRepo;
	
	@Override
	public String getActiveGstSlabs(String token) throws JsonProcessingException {
		List<Gst> gstSlabs = gstRepo.getGstSlabsByStatus(AppConstants.ACTIVE_RECORD_STATUS);
		return ItemFilter.filterGstSlabsEntityList(gstSlabs);
	}

	@Override
	public String getActiveItemTypes(String token) throws JsonProcessingException {
		List<ItemType> itemTypes = itemTypeRepo.getItemTypesByStatus(AppConstants.ACTIVE_RECORD_STATUS);
		return ItemFilter.filterItemTypeEntityList(itemTypes);
	}

	

}
