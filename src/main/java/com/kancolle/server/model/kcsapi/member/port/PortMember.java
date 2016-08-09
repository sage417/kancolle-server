package com.kancolle.server.model.kcsapi.member.port;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.utils.jackson.NumericBooleanSerializer;

@JsonPropertyOrder(value = {
        "memberId", "nickName", "nickNameId", "activeFlag",
        "startTime", "level", "rank", "experience",
        "fleetName", "comment", "commentId", "maxChara",
        "maxSlotItem", "maxKagu", "playTime", "tutorial",
        "furniture", "deckCount", "kdockCount", "ndockCount",
        "fCoin", "attackWin", "attackLose", "missionCount",
        "missionSuccess", "practiceWin", "practiceLose", "practiceChallenged",
        "practiceChallengedWin", "firstFlag", "tutorialProgress", "pvp",
        "medals","api_large_dock"
})
public class PortMember extends Member {

    private static final long serialVersionUID = 4885012780581892057L;

    @JsonProperty(value = "api_large_dock")
    @JSONField(ordinal = 999)
    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean api_large_dock;

    public boolean getApi_large_dock() {
        return api_large_dock;
    }

    public void setApi_large_dock(boolean api_large_dock) {
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
