package com.kancolle.server.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"api_result", "api_result_msg", "api_data"})
public class APIResponse<T> {

    private static final String SUCCESS_MSG = "成功";
    private static final String FAILED_MSG = "失败";

    private static final int SUCCESS_CODE = 1;

    public static final APIResponse<Object> EMPTY_SUCCESS_RESPONSE = new APIResponse<>(SUCCESS_CODE, SUCCESS_MSG);
    public static final APIResponse<Object> EMPTY_FAILED_RESPONSE = new APIResponse<>(100, FAILED_MSG);

    @JsonProperty(value = "api_result")
    @JSONField(ordinal = 1)
    private int api_result;

    @JsonProperty(value = "api_result_msg")
    @JSONField(ordinal = 2)
    private String api_result_msg;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "api_data")
    @JSONField(ordinal = 3)
    private T api_data;

    public APIResponse() {
        this.api_result = SUCCESS_CODE;
        this.api_result_msg = SUCCESS_MSG;
    }

    public APIResponse(int api_result, String api_result_msg) {
        this.api_result = api_result;
        this.api_result_msg = api_result_msg;
    }

    public APIResponse(Builder<T> builder) {
        this.api_result = builder.api_result;
        this.api_result_msg = builder.api_result_msg;
        this.api_data = builder.api_data;
    }

    public static <T> APIResponse.Builder<T> builder() {
        return new APIResponse.Builder<>();
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

    public static class Builder<T> {

        private int api_result = SUCCESS_CODE;

        private String api_result_msg = SUCCESS_MSG;

        private T api_data;

        public Builder<T> result(final int result) {
            this.api_result = result;
            return this;
        }

        public Builder<T> msg(final String msg) {
            this.api_result_msg = msg;
            return this;
        }

        public Builder<T> data(final T data) {
            this.api_data = data;
            return this;
        }

        public APIResponse<T> build() {
            return new APIResponse<>(this);
        }
    }
}
