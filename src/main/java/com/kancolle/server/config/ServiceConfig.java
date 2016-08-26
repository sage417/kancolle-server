package com.kancolle.server.config;

import com.google.common.eventbus.EventBus;
import com.kancolle.server.utils.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII;
import static com.fasterxml.jackson.databind.MapperFeature.DEFAULT_VIEW_INCLUSION;

/**
 * Package: com.kancolle.server.config
 * Author: mac
 * Date: 16/7/27
 */
@Configuration
@ImportResource(value = "classpath:spring/spring-context.xml")
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
        return factoryBean;
    }
}
