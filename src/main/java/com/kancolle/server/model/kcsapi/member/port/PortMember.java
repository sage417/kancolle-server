package com.kancolle.server.model.kcsapi.member.port;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.member.Member;

public class PortMember extends Member {

    @JSONField(ordinal = 999)
    private int api_large_dock;

    public int getApi_large_dock() {
        return api_large_dock;
    }

    public void setApi_large_dock(int api_large_dock) {
        this.api_large_dock = api_large_dock;
    }
}