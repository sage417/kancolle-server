package com.kancolle.server.model.kcsapi.member;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

@Alias("MemberNdock")
public class MemberNdock {

    @JSONField(ordinal = 1)
    private long api_member_id;

    @JSONField(ordinal = 2)
    private int api_id;

    @JSONField(ordinal = 3)
    private int api_state;

    @JSONField(ordinal = 4)
    private long api_ship_id;

    @JSONField(ordinal = 5)
    private long api_complete_time;

    @JSONField(ordinal = 6)
    private String api_complete_time_str;

    @JSONField(ordinal = 7)
    private int api_item1;

    @JSONField(ordinal = 8)
    private int api_item2;

    @JSONField(ordinal = 9)
    private int api_item3;

    @JSONField(ordinal = 10)
    private int api_item4;

    public long getApi_complete_time() {
        return api_complete_time;
    }

    public String getApi_complete_time_str() {
        return api_complete_time_str;
    }

    public int getApi_id() {
        return api_id;
    }

    public int getApi_item1() {
        return api_item1;
    }

    public int getApi_item2() {
        return api_item2;
    }

    public int getApi_item3() {
        return api_item3;
    }

    public int getApi_item4() {
        return api_item4;
    }

    public long getApi_member_id() {
        return api_member_id;
    }

    public long getApi_ship_id() {
        return api_ship_id;
    }

    public int getApi_state() {
        return api_state;
    }

    public void setApi_complete_time(long api_complete_time) {
        this.api_complete_time = api_complete_time;
    }

    public void setApi_complete_time_str(String api_complete_time_str) {
        this.api_complete_time_str = api_complete_time_str;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public void setApi_item1(int api_item1) {
        this.api_item1 = api_item1;
    }

    public void setApi_item2(int api_item2) {
        this.api_item2 = api_item2;
    }

    public void setApi_item3(int api_item3) {
        this.api_item3 = api_item3;
    }

    public void setApi_item4(int api_item4) {
        this.api_item4 = api_item4;
    }

    public void setApi_member_id(long api_member_id) {
        this.api_member_id = api_member_id;
    }

    public void setApi_ship_id(long api_ship_id) {
        this.api_ship_id = api_ship_id;
    }

    public void setApi_state(int api_state) {
        this.api_state = api_state;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + api_id;
        result = prime * result + (int) (api_member_id ^ (api_member_id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MemberNdock other = (MemberNdock) obj;
        if (api_id != other.api_id)
            return false;
        if (api_member_id != other.api_member_id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MemberNdock [api_member_id=%s, api_id=%s, api_state=%s, api_ship_id=%s, api_complete_time_str=%s, api_item1=%s, api_item2=%s, api_item3=%s, api_item4=%s]",
                api_member_id, api_id, api_state, api_ship_id, api_complete_time_str, api_item1, api_item2, api_item3, api_item4);
    }
}
