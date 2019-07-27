package com.octopus.service.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.octopus.service.domain.ApiResponse;
import com.octopus.service.service.DeliveryService;

@RestController
@RequestMapping(value = "/delivery")
public class DeliveryController {

    private ApiResponse response = null;

    @Value("${token.header}")
    private String tokenHeader;
    @Autowired
    private DeliveryService deliveryService;

    @PreAuthorize("hasRole('ROLE_DELIVERY_BOY')")
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getDeliveryList(HttpServletRequest request,
            @RequestParam(name = "filterBy", required = false) String filterBy,
            @RequestParam(value = "q", required = false) final String search,
            final Pageable pageable) {
        String token = request.getHeader(tokenHeader);
        response = deliveryService.getDeliveryList(token, filterBy, search, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
