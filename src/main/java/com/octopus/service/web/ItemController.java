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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octopus.service.domain.ApiResponse;
import com.octopus.service.domain.model.Item;
import com.octopus.service.service.ItemService;
import com.octopus.service.util.AppMessages;
import com.octopus.service.util.AppUtil;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

	private ApiResponse response = null;

	@Value("${token.header}")
    private String tokenHeader;
	
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> addItem(HttpServletRequest request,
											   @RequestBody Item item) throws JsonProcessingException {
		String token = request.getHeader(tokenHeader);
		itemService.addItem(token, item);
		response = AppUtil.frameSuccessResponse(
				HttpStatus.OK.value(), 
				AppMessages.SUCCESSFUL_ITEM_ADD);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateItem(HttpServletRequest request,
												  @RequestBody Item item) throws JsonProcessingException {
		String token = request.getHeader(tokenHeader);
		itemService.updateItem(token, item);
		response = AppUtil.frameSuccessResponse(
				HttpStatus.OK.value(), 
				AppMessages.SUCCESSFUL_ITEM_UPDATE);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{itemId}/upload",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> imageUpload(
			final HttpServletRequest request,
			@PathVariable("itemId") final Long itemId,
			@RequestParam("file") final MultipartFile file)
			throws JsonProcessingException {
		final String token = request.getHeader(tokenHeader);
		response = itemService.uploadImage(token, itemId, file);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}
}
