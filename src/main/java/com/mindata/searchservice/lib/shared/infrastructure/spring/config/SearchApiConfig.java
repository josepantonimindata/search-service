package com.mindata.searchservice.lib.shared.infrastructure.spring.config;

import com.mindata.searchservice.lib.shared.infrastructure.spring.ApiExceptionMiddleware;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class SearchApiConfig {
    private final RequestMappingHandlerMapping mapping;
    
    public SearchApiConfig(RequestMappingHandlerMapping mapping) {this.mapping = mapping;}
    
    @Bean
    public FilterRegistrationBean<ApiExceptionMiddleware> apiExceptionMiddleware() {
        FilterRegistrationBean<ApiExceptionMiddleware> registrationBean = new FilterRegistrationBean<>();
        
        registrationBean.setFilter(new ApiExceptionMiddleware(mapping));
        
        return registrationBean;
    }
}
