package com.octopus.service.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.SuccessResponse;
import com.octopus.service.domain.model.Item;
import com.octopus.service.domain.model.UserAddress;
import com.octopus.service.service.AdminService;
import com.octopus.service.service.ItemService;
import com.octopus.service.util.AppResponse;
import com.octopus.service.util.AppUtil;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	
	private SuccessResponse response = null;
	
	@Value("${token.header}")
    private String tokenHeader;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ItemService itemService;
	
	
	
	

}
