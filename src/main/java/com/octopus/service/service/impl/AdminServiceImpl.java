package com.octopus.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.octopus.service.domain.model.Gst;
import com.octopus.service.domain.model.Item;
import com.octopus.service.domain.model.ItemType;
import com.octopus.service.domain.repository.GstRepo;
import com.octopus.service.domain.repository.ItemRepo;
import com.octopus.service.domain.repository.ItemTypeRepo;
import com.octopus.service.exception.OctopusPermissionException;
import com.octopus.service.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private GstRepo gstRepo;
	
	@Autowired
	private ItemTypeRepo itemTypeRepo;
	
	@Autowired
	private ItemRepo itemRepo;

	

	@Override
	public String orderListGrpByItem() {
		return null;
	}

	@Override
	public String orderListSrtByLocality() {
		return null;
	}

	@Override
	public void updateOrderDelivery() {
		
	}

}
