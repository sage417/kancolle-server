package com.kancolle.server.model.kcsapi.member.port;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.member.Member;

public class PortMember extends Member {

    /**
     * 
     */
    private static final long serialVersionUID = 4885012780581892057L;
    @JSONField(ordinal = 999)
    private int api_large_dock;

    public int getApi_large_dock() {
        return api_large_dock;
    }

    public void setApi_large_dock(int api_large_dock) {
        this.api_large_dock = api_large_dock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return getMemberId() == member.getMemberId();

    }

    @Override
    public int hashCode() {
        return (int) (getMemberId() ^ (getMemberId() >>> 32));
    }


}
