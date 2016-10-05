package com.kancolle.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Package: com.kancolle.server.config
 * Author: mac
 * Date: 16/9/12
 */
@Configuration
public class MvcConfig {

    private static final String JSON_PREFIX = "svdata=";

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter(@Autowired ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setSupportedMediaTypes(Lists.newArrayList(MediaType.TEXT_HTML));
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        jackson2HttpMessageConverter.setJsonPrefix(JSON_PREFIX);
        return jackson2HttpMessageConverter;
    }
}
