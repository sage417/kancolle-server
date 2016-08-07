package com.kancolle.server.model.po.duty;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "dutyNo", "api_category", "api_type", "state",
        "api_title", "api_detail", "api_get_material", "api_bonus_flag",
        "api_progress_flag", "api_invalid_flag"
})
@Alias("MemberDuty")
public class MemberDuty implements Serializable {

    /** 未受領 **/
    public static final int STATE_AVILABLE = 1;

    /** 遂行中 **/
    public static final int STATE_PROCESSING = 2;

    /** 達成 **/
    public static final int STATE_FINISH = 3;

    private static final long serialVersionUID = 4233366344226823086L;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private Duty duty;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private String memberId;

    @JsonProperty(value = "api_no")
    @JSONField(name = "api_no", ordinal = 1)
    private int dutyNo;

    @JsonProperty(value = "api_category")
    @JSONField(name = "api_category", ordinal = 2)
    public int gerCategory() {
        return getDuty().getCategory();
    }

    @JsonProperty(value = "api_type")
    @JSONField(name = "api_type", ordinal = 3)
    public int getType() {
        return getDuty().getType();
    }

    @JsonProperty(value = "api_state")
    @JSONField(name = "api_state", ordinal = 4)
    private int state;

    @JsonProperty(value = "api_title")
    @JSONField(name = "api_title", ordinal = 5)
    public String getTitle() {
        return getDuty().getTitle();
    }

    @JsonProperty(value = "api_detail")
    @JSONField(name = "api_detail", ordinal = 6)
    public String getDetail() {
        return getDuty().getDetail();
    }

    @JsonProperty(value = "api_get_material")
    @JSONField(name = "api_get_material", ordinal = 7)
    public int[] getMaterial() {
        return getDuty().getMaterial();
    }

    @JsonProperty(value = "api_bonus_flag")
    @JSONField(name = "api_bonus_flag", ordinal = 8)
    public int getBonusFlag() {
        return getDuty().getBonusFlag() == Duty.BONUS_TYPE_SHIP ? 2 : 1;
    }

    @JsonProperty(value = "api_progress_flag")
    @JSONField(name = "api_progress_flag", ordinal = 9)
    private int progressFlag;

    @JsonProperty(value = "api_invalid_flag")
    @JSONField(name = "api_invalid_flag", ordinal = 10)
    public int getInvalidFlag() {
        return getDuty().getInvalidFlag();
    }

    @JSONField(serialize = false, deserialize = false)
    private int counter;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getDutyNo() {
        return dutyNo;
    }

    public void setDutyNo(int dutyNo) {
        this.dutyNo = dutyNo;
    }

    public Duty getDuty() {
        return duty;
    }

    public void setDuty(Duty duty) {
        this.duty = duty;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getProgressFlag() {
        return progressFlag;
    }

    public void setProgressFlag(int progressFlag) {
        this.progressFlag = progressFlag;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + dutyNo;
        result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
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
        MemberDuty other = (MemberDuty) obj;
        if (dutyNo != other.dutyNo)
            return false;
        if (memberId == null) {
            if (other.memberId != null)
                return false;
        } else if (!memberId.equals(other.memberId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MemberDuty [duty=" + duty + ", memberId=" + memberId + "]";
    }
}
