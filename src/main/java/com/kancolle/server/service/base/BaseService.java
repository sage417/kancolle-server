package com.kancolle.server.service.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Package: com.kancolle.server.service.base
 * Author: mac
 * Date: 16/4/11
 */
@Service
public class BaseService {

    protected static final String EMPTY_OBJECT_JSON = "{}";

    @Autowired
    private ObjectMapper objectMapper;

    protected String writeJson(Object obj, String defaultStr) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return defaultStr;
        }
    }

    protected <T> T readJson(String content, Class<T> clazz) {
        try {
            return objectMapper.readValue(content, clazz);
        } catch (IOException e) {
            return BeanUtils.instantiate(clazz);
        }
    }
}
