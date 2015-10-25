package com.kancolle.server.model.po.member;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

@Alias("MemberNdock")
public class MemberNdock implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 7814147582159739062L;
    /** 未开启 */
    public static final int STATE_UNAVILABLE = -1;
    /** 可用 */
    public static final int STATE_AVILABLE = 0;
    /** 占用中 */
    public static final int STATE_USING = 1;

    @JSONField(ordinal = 1, name = "api_member_id")
    private long memberId;

    @JSONField(ordinal = 2, name = "api_id")
    private int dockId;

    @JSONField(ordinal = 3, name = "api_state")
    private int state;

    @JSONField(ordinal = 4, name = "api_ship_id")
    private long memberShipId;

    @JSONField(ordinal = 5, name = "api_complete_time")
    private long completeTime;

    @JSONField(ordinal = 6, name = "api_complete_time_str")
    private String completeTimeStr;

    @JSONField(ordinal = 7, name = "api_item1")
    private int item1;

    @JSONField(ordinal = 8, name = "api_item2")
    private int item2;

    @JSONField(ordinal = 9, name = "api_item3")
    private int item3;

    @JSONField(ordinal = 10, name = "api_item4")
    private int item4;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public int getDockId() {
        return dockId;
    }

    public void setDockId(int dockId) {
        this.dockId = dockId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getMemberShipId() {
        return memberShipId;
    }

    public void setMemberShipId(long memberShipId) {
        this.memberShipId = memberShipId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + dockId;
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
        MemberNdock other = (MemberNdock) obj;
        if (dockId != other.dockId)
            return false;
        if (memberId != other.memberId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MemberNdock [memberId=%s, dockId=%s, state=%s, completeTimeStr=%s]", memberId, dockId, state, completeTimeStr);
    }
}
