package com.kancolle.server.model.kcsapi.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kancolle.server.model.po.map.MemberMapInfo;

import java.util.List;

/**
 * Created by J.K.SAGE on 2017/1/8.
 */
public class MemberMapInfoResult {

    @JsonProperty("api_map_info")
    private List<MemberMapInfo> memberMapInfos;

    public MemberMapInfoResult(final List<MemberMapInfo> memberMapInfos) {
        this.memberMapInfos = memberMapInfos;
    }

    public List<MemberMapInfo> getMemberMapInfos() {
        return memberMapInfos;
    }

    public void setMemberMapInfos(List<MemberMapInfo> memberMapInfos) {
        this.memberMapInfos = memberMapInfos;
    }
}
