package com.kancolle.server.model.kcsapi.start.requireInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by J.K.SAGE on 2016/8/8 0008.
 */
@JsonPropertyOrder(value = {
        "member_id", "firstFlag"
})
public class Basic {

    @JsonProperty(value = "api_member_id")
    private long member_id;

    @JsonProperty(value = "api_firstflag")
    private int firstFlag;

    public Basic() {
    }

    public Basic(final long member_id, final int firstFlag) {
        this.member_id = member_id;
        this.firstFlag = firstFlag;
    }

    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public int getFirstFlag() {
        return firstFlag;
    }

    public void setFirstFlag(int firstFlag) {
        this.firstFlag = firstFlag;
    }
}
