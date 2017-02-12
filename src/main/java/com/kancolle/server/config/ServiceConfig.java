package com.kancolle.server.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.eventbus.EventBus;
import com.kancolle.server.utils.SpringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.web.client.RestTemplate;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII;
import static com.fasterxml.jackson.databind.MapperFeature.DEFAULT_VIEW_INCLUSION;

/**
 * Package: com.kancolle.server.config
 * Author: mac
 * Date: 16/7/27
 */
@Configuration
@MapperScan(basePackages = {"com.kancolle.server.mapper"})
@ImportResource(value = {"classpath:spring/spring-cache.xml", "classpath:spring/db-context.xml"})
public class ServiceConfig {

    @Bean(name = "dutyBus")
    public EventBus getDutyEventBus() {
        return new EventBus();
    }

    @Bean(name = "springUtils")
    public static SpringUtils getSpringUtils() {
        return new SpringUtils();
    }

    @Bean(name = "jackson2ObjectMapper")
    public Jackson2ObjectMapperFactoryBean getJackson2ObjectMapperFactoryBean() {
        Jackson2ObjectMapperFactoryBean factoryBean = new Jackson2ObjectMapperFactoryBean();
        factoryBean.setFeaturesToEnable(ESCAPE_NON_ASCII, DEFAULT_VIEW_INCLUSION);
        factoryBean.setFeaturesToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return factoryBean;
    }

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        HttpClient httpClient = HttpClientBuilder
                .create()
                .disableCookieManagement()
                .build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(requestFactory);
    }
}
