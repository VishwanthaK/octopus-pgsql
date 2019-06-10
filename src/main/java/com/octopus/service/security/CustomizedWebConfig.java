package com.octopus.service.security;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.octopus.service.util.querygenerator.SearchOperator;
import com.octopus.service.util.querygenerator.SearchQueryVisitorFactory;
import cz.jirutka.rsql.parser.RSQLParser;

@Configuration
public class CustomizedWebConfig extends WebMvcConfigurerAdapter {

    private static final String PAGE = "page-number";
    private static final String SIZE = "page-size";
    private static final String SORT = "order-by";
    private static final Integer DEFAULT_PAGE_NUMBER = 0;

    @Autowired
    private LoggerInterceptor loggerInterceptor;

    @Value("${pagination.default.page-size}")
    private Integer defaultPageSize;
    @Value("${pagination.max-page-size}")
    private Integer maxPageSize;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // set sorting query parameters
        SortHandlerMethodArgumentResolver sortResolver = new SortHandlerMethodArgumentResolver();
        sortResolver.setSortParameter(SORT);

        // set pagination query parameters
        PageableHandlerMethodArgumentResolver pageResolver = new PageableHandlerMethodArgumentResolver(sortResolver);
        Pageable defaultPageable = new PageRequest(DEFAULT_PAGE_NUMBER, defaultPageSize);
        pageResolver.setOneIndexedParameters(true); // set 1-indexed page-number in query parameter
        pageResolver.setPageParameterName(PAGE);
        pageResolver.setSizeParameterName(SIZE);
        pageResolver.setMaxPageSize(maxPageSize);
        pageResolver.setFallbackPageable(defaultPageable); // default pagination

        argumentResolvers.add(pageResolver);
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientRequestInterceptor());
        registry.addInterceptor(loggerInterceptor);
    }

    @Bean
    public LocaleResolver localeResolver() {
        // get language token from "Accept-Language" header
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
        acceptHeaderLocaleResolver.setDefaultLocale(Locale.US);
        return acceptHeaderLocaleResolver;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RequestHeaderData requestHeaderData() {
        // stores tenant id and request id which can be accessed
        // anywhere in the application
        return new RequestHeaderData();
    }

    @Bean
    public ClientRequestInterceptor clientRequestInterceptor() {
        // set requestId and tenantId header values to RequestMeta
        return new ClientRequestInterceptor(requestHeaderData());
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        restTemplate.setErrorHandler(new CustomRestTemplateResponseErrorHandler());

        return restTemplate;
    }

    @Bean
    public RSQLParser rsqlParser() {
        return new RSQLParser(SearchOperator.getAllOperators());
    }

    @Bean
    public SearchQueryVisitorFactory searchQueryVisitorFactory() {
        return new SearchQueryVisitorFactory();
    }

    /**
     * Use this class as error handler for the restTemplate bean so that exceptions are
     * not thrown for http statuses other than 2XX.
     */
    private class CustomRestTemplateResponseErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {

        }
    }

    //Set by default All Rest controller return Json Type.
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8);
    }
}
