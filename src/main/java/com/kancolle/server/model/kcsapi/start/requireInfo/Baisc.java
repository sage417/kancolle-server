package com.kancolle.server.model.kcsapi.start.requireInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by J.K.SAGE on 2016/8/8 0008.
 */
@JsonPropertyOrder(value = {
    "api_member_id","api_firstflag"
})
public class Baisc {

    @JsonProperty(value = "api_member_id")
    private long api_member_id;

    @JsonProperty(value = "api_firstflag")
    private int api_firstflag;

    public long getApi_member_id() {
        return api_member_id;
    }

    public void setApi_member_id(long api_member_id) {
        this.api_member_id = api_member_id;
    }

    public int getApi_firstflag() {
        return api_firstflag;
    }

    public void setApi_firstflag(int api_firstflag) {
        this.api_firstflag = api_firstflag;
    }
}
