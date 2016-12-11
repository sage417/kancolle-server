package com.kancolle.server.model.kcsapi.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "api_member_id", "api_id", "api_value"
})
public class MemberMaterialDto implements Serializable {

    @JsonProperty(value = "api_member_id")
    private long member_id;

    @JsonProperty(value = "api_id")
    private int id;

    @JsonProperty(value = "api_value")
    private int value;

    public MemberMaterialDto() {
    }

    public MemberMaterialDto(long member_id, int id, int value) {
        this.member_id = member_id;
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public long getMember_id() {
        return member_id;
    }

    public int getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
