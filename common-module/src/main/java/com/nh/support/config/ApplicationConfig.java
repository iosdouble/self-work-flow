package com.nh.support.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nh.support.client.jdbc.JdbcClient;
import com.nh.support.client.rest.RestClient;
import com.nh.support.client.rest.ServiceUrl;
import com.nh.support.exception.ExceptionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.util.Locale;

/**
 * @Classname ApplicationConfig
 * @Description TODO
 * @Date 2020/4/2 6:43 PM
 * @Created by nihui
 */
@Configuration
public class ApplicationConfig {

    private final MessageSource messageSource;
    private final JdbcTemplate jdbcTemplate;
    private final RestTemplate restTemplate;

    @Autowired
    public ApplicationConfig(MessageSource messageSource, JdbcTemplate jdbcTemplate, RestTemplate restTemplate) {
        this.messageSource = messageSource;
        this.jdbcTemplate = jdbcTemplate;
        this.restTemplate = restTemplate;
    }

    @Bean
    @ConfigurationProperties(prefix = "serviceurl")
    public ServiceUrl serviceUrl() {
        return new ServiceUrl();
    }

    @Bean
    public ExceptionFactory exceptionFactory() {
        return new ExceptionFactory(messageSource);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CHINA));
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    @Bean
    public JdbcClient jdbcClient() {
        return new JdbcClient(jdbcTemplate);
    }

    @Bean
    public RestClient restClient() {
        return new RestClient(restTemplate, serviceUrl());
    }
}
