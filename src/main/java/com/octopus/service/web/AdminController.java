package com.octopus.service.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.ApiResponse;
import com.octopus.service.service.AdminService;
import com.octopus.service.service.ItemService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
	
	private ApiResponse response = null;
	
	@Value("${token.header}")
    private String tokenHeader;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ItemService itemService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping( value = "/{itemTypeId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getItem(HttpServletRequest request,
			@PathVariable("itemTypeId") Long itemtypeId,
			Pageable pageable) throws JsonProcessingException {
		String token = request.getHeader(tokenHeader);
		String items = itemService.getItem(token, itemtypeId, pageable);
		return new ResponseEntity<String>(items, HttpStatus.OK);
	}
	
	

}
