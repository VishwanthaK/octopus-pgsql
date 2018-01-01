package com.octopus.service.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.service.ItemService;
import com.octopus.service.service.MasterService;

@RestController
@RequestMapping(value = "/master")
public class MasterController {
	
	@Value("${token.header}")
    private String tokenHeader;
	
	@Autowired
	private MasterService masterService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping( value = "/get/item/types",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getItemTypes(HttpServletRequest request) 
    		throws JsonProcessingException {
		String token = request.getHeader(tokenHeader);
		String itemTypes = masterService.getActiveItemTypes(token);
        return new ResponseEntity<String>(itemTypes, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping( value = "/get/gst/slabs",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getGstSlabs(HttpServletRequest request) 
    		throws JsonProcessingException {
		String token = request.getHeader(tokenHeader);
		String gstSlabs = masterService.getActiveGstSlabs(token);
        return new ResponseEntity<String>(gstSlabs, HttpStatus.OK);
	}
	

}
