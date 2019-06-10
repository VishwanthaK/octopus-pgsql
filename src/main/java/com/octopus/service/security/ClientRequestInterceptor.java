package com.octopus.service.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientRequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(ClientRequestInterceptor.class);

    private RequestHeaderData requestHeaderData;

    public ClientRequestInterceptor(RequestHeaderData requestHeaderData) {
        this.requestHeaderData = requestHeaderData;
    }

    /**
     * Store requestId and tenantId for each request
     * in requestHeaderData.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String requestIdHeader = request.getHeader("X-Request-Id");
        final String authToken = request.getHeader("X-Auth-Token");
        final String xDateTimeFormat = request.getHeader("X-Date-Time-Format");

        requestHeaderData.setRequestId(requestIdHeader);
        requestHeaderData.setToken(authToken);
        requestHeaderData.setDateTimeFormat(xDateTimeFormat);

        return true;
    }

}
