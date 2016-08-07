package com.kancolle.server.model.po.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "memberId", "kdockId", "state", "createShipId",
        "completeTime", "completeTimeStr", "item1", "item2",
        "item3", "item4", "item5"
})
@Alias("MemberKdock")
public class MemberKdock implements Serializable {

    private static final long serialVersionUID = -8881685976219672841L;

    public static final int STATUS_UNAVILABLE = -1;

    public static final int STATUS_AVILABLE = 0;

    public static final int STATUS_BUILDING = 2;

    public static final int STATUS_FINISHED = 3;

    @JsonProperty(value = "api_member_id")
    @JSONField(ordinal = 1, name = "api_member_id")
    private long memberId;

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 2, name = "api_id")
    private int kdockId;

    @JsonProperty(value = "api_state")
    @JSONField(ordinal = 3, name = "api_state")
    private int state;

    @JsonProperty(value = "api_created_ship_id")
    @JSONField(ordinal = 4, name = "api_created_ship_id")
    private int createShipId;

    @JsonProperty(value = "api_complete_time")
    @JSONField(ordinal = 5, name = "api_complete_time")
    private long completeTime;

    @JsonProperty(value = "api_complete_time_str")
    @JSONField(ordinal = 6, name = "api_complete_time_str")
    private String completeTimeStr;

    @JsonProperty(value = "api_item1")
    @JSONField(ordinal = 7, name = "api_item1")
    private int item1;

    @JsonProperty(value = "api_item2")
    @JSONField(ordinal = 8, name = "api_item2")
    private int item2;

    @JsonProperty(value = "api_item3")
    @JSONField(ordinal = 9, name = "api_item3")
    private int item3;

    @JsonProperty(value = "api_item4")
    @JSONField(ordinal = 10, name = "api_item4")
    private int item4;

    @JsonProperty(value = "api_item5")
    @JSONField(ordinal = 11, name = "api_item5")
    private int item5;

    public MemberKdock() {
    }

    public MemberKdock(long memberId, int kdockId, int state) {
        this.memberId = memberId;
        this.kdockId = kdockId;
        this.state = state;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public int getKdockId() {
        return kdockId;
    }

    public void setKdockId(int kdockId) {
        this.kdockId = kdockId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCreateShipId() {
        return createShipId;
    }

    public void setCreateShipId(int createShipId) {
        this.createShipId = createShipId;
    }

    public long getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(long completeTime) {
        this.completeTime = completeTime;
    }

    public String getCompleteTimeStr() {
        return completeTimeStr;
    }

    public void setCompleteTimeStr(String completeTimeStr) {
        this.completeTimeStr = completeTimeStr;
    }

    public int getItem1() {
        return item1;
    }

    public void setItem1(int item1) {
        this.item1 = item1;
    }

    public int getItem2() {
        return item2;
    }

    public void setItem2(int item2) {
        this.item2 = item2;
    }

    public int getItem3() {
        return item3;
    }

    public void setItem3(int item3) {
        this.item3 = item3;
    }

    public int getItem4() {
        return item4;
    }

    public void setItem4(int item4) {
        this.item4 = item4;
    }

    public int getItem5() {
        return item5;
    }

    public void setItem5(int item5) {
        this.item5 = item5;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + createShipId;
        result = prime * result + (int) (memberId ^ (memberId >>> 32));
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
        MemberKdock other = (MemberKdock) obj;
        if (createShipId != other.createShipId)
            return false;
        if (memberId != other.memberId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MemberKdock [memberId=%s, kdockId=%s, state=%s, createShipId=%s, completeTimeStr=%s]", memberId, kdockId, state, createShipId, completeTimeStr);
    }
}
