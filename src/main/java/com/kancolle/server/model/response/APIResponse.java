package com.kancolle.server.model.response;

import com.alibaba.fastjson.annotation.JSONField;

public class APIResponse<T> {
    private static final String DEFAULT_RESULT_MSG = "成功";

    @JSONField(ordinal = 1)
    private int api_result = 1;

    @JSONField(ordinal = 2)
    private String api_result_msg = DEFAULT_RESULT_MSG;

    @JSONField(ordinal = 3)
    private T api_data;

    public int getApi_result() {
        return api_result;
    }

    public String getApi_result_msg() {
        return api_result_msg;
    }

    public void setApi_result_msg(String api_result_msg) {
        this.api_result_msg = api_result_msg;
    }

    public T getApi_data() {
        return api_data;
    }

    public APIResponse<T> setApi_data(T api_data) {
        this.api_data = api_data;
        return this;
    }

    public void setApi_result(int api_result) {
        this.api_result = api_result;
    }
}
