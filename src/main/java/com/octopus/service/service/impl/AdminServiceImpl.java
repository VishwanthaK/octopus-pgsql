package com.octopus.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.octopus.service.domain.repository.GstRepository;
import com.octopus.service.domain.repository.ItemRepository;
import com.octopus.service.domain.repository.ItemTypeRepository;
import com.octopus.service.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private GstRepository gstRepository;
	
	@Autowired
	private ItemTypeRepository itemTypeRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	

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
