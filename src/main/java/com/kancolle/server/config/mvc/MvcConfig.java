package com.kancolle.server.config.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;
import java.util.List;

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


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(Single.class)
    public SingleReturnValueHandler singleReturnValueHandler() {
        return new SingleReturnValueHandler();
    }


    @Configuration
    public static class RxJavaWebConfiguration {

        @Autowired
        private List<AsyncHandlerMethodReturnValueHandler> handlers = Collections.emptyList();

        @Bean
        public WebMvcConfigurer rxJavaWebMvcConfiguration() {
            return new WebMvcConfigurerAdapter() {
                @Override
                public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
                    returnValueHandlers.addAll(handlers);
                }
            };
        }
    }
}
