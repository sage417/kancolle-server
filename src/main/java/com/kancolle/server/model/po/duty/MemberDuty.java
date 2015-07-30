package com.kancolle.server.model.po.duty;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

@Alias("MemberDuty")
public class MemberDuty implements Serializable {

    private static final long serialVersionUID = -4807950044590774159L;

    @JSONField(name = "api_no", ordinal = 1)
    private int no;

    @JSONField(name = "api_category", ordinal = 2)
    private int category;

    @JSONField(name = "api_type", ordinal = 3)
    private int type;

    @JSONField(name = "api_title", ordinal = 4)
    private int state;

    @JSONField(name = "api_title", ordinal = 5)
    private String title;

    @JSONField(name = "api_detail", ordinal = 6)
    private String detail;

    @JSONField(name = "api_get_material", ordinal = 7)
    private int[] getMaterial;

    @JSONField(name = "api_bonus_flag", ordinal = 8)
    private int bonusFlag;

    @JSONField(name = "api_progress_flag", ordinal = 9)
    private int progressFlag;

    @JSONField(name = "api_invalid_flag", ordinal = 10)
    private int invalidFlag;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int[] getGetMaterial() {
        return getMaterial;
    }

    public void setGetMaterial(int[] getMaterial) {
        this.getMaterial = getMaterial;
    }

    public int getBonusFlag() {
        return bonusFlag;
    }

    public void setBonusFlag(int bonusFlag) {
        this.bonusFlag = bonusFlag;
    }

    public int getProgressFlag() {
        return progressFlag;
    }

    public void setProgressFlag(int progressFlag) {
        this.progressFlag = progressFlag;
    }

    public int getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(int invalidFlag) {
        this.invalidFlag = invalidFlag;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + no;
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
        if (no != other.no)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MemberDuty [title=" + title + ", state=" + state + "]";
    }
}
