package com.octopus.service.service;

import com.octopus.service.domain.model.Item;

public interface AdminService {
	
	String orderListGrpByItem();
	
	String orderListSrtByLocality();
	
	void updateOrderDelivery();

}
