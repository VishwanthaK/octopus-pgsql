package com.octopus.service.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(LoggerInterceptor.class);
    private static final List<Integer> warningStatuses = Arrays.asList(403, 415);

    @Autowired
    private RequestHeaderData requestHeaderData;

    @Value("${spring.application.name}")
    private String serviceName;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Integer httpStatus = response.getStatus();

        MDC.put("serviceName", serviceName);
        MDC.put("protocol", request.getProtocol());
        MDC.put("method", request.getMethod());
        MDC.put("request", getFullURL(request));
        MDC.put("status", String.valueOf(httpStatus));
        MDC.put("requestId", requestHeaderData.getRequestId());

        if (ex != null) {
            LOG.error(generateLogMessage(httpStatus), requestHeaderData.getException());
        } else if (warningStatuses.contains(httpStatus)) {
            LOG.warn(generateLogMessage(httpStatus), requestHeaderData.getException());
        }else if (httpStatus >= 500) {
            LOG.error(generateLogMessage(httpStatus), requestHeaderData.getException());
        } else {
            LOG.info(generateLogMessage(httpStatus), requestHeaderData.getException());
        }

        MDC.clear();
    }

    private String generateLogMessage(Integer httpStatus) {
        if (httpStatus < 200) {
            return "informal";
        }

        if (httpStatus >= 200 && httpStatus < 300) {
            return "success";
        }

        if (httpStatus >= 300 && httpStatus < 400) {
            return "redirection";
        }

        if (httpStatus >= 400 && httpStatus < 500) {
            return "client error";
        }

        return "internal server error";
    }

    private String getFullURL(HttpServletRequest request) {
        String requestURL = request.getRequestURI();
        String queryString = request.getQueryString();
        StringBuffer fullURL = new StringBuffer();
        fullURL.append(requestURL);

        if (queryString != null) {
            fullURL.append("?").append(queryString);
        }

        return fullURL.toString();
    }

}
