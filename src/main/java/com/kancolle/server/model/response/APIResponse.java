package com.kancolle.server.model.response;

import com.alibaba.fastjson.annotation.JSONField;

public class APIResponse<T> {

    private static final String SUCCESS_MSG = "成功";
    private static final String FAILED_MSG = "失败";

    public static final APIResponse<Object> EMPTY_SUCCESS_RESPONSE = new APIResponse<>(1, SUCCESS_MSG);
    public static final APIResponse<Object> EMPTY_FAILED_RESPONSE = new APIResponse<>(100, FAILED_MSG);

    @JSONField(ordinal = 1)
    private int api_result;

    @JSONField(ordinal = 2)
    private String api_result_msg;

    @JSONField(ordinal = 3)
    private T api_data;

    public APIResponse() {
    }

    public APIResponse(int api_result, String api_result_msg) {
        this.api_result = api_result;
        this.api_result_msg = api_result_msg;
    }

    public T getApi_data() {
        return api_data;
    }

    public int getApi_result() {
        return api_result;
    }

    public String getApi_result_msg() {
        return api_result_msg;
    }

    public APIResponse<T> setApi_data(T api_data) {
        this.api_data = api_data;
        return this;
    }

    public void setApi_result(int api_result) {
        this.api_result = api_result;
    }

    public void setApi_result_msg(String api_result_msg) {
        this.api_result_msg = api_result_msg;
    }
}
