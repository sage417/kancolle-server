package com.kancolle.server.model.po.furniture;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

@Alias("MemberFurniture")
public class MemberFurniture {

    @JSONField(ordinal = 1, name = "api_member_id")
    private long member_id;

    @JSONField(serialize = false, deserialize = false)
    private Furniture furniture;

    @JSONField(ordinal = 2, name = "api_id")
    public int returnId() {
        return furniture.getFurnitureId();
    }

    @JSONField(ordinal = 3, name = "api_furniture_type")
    public int returnFurnitureType() {
        return furniture.getType();
    }

    @JSONField(ordinal = 4, name = "api_furniture_no")
    public int returnFurniureNo() {
        return furniture.getNo();
    }

    @JSONField(ordinal = 5, name = "api_furniture_id")
    public int returnFurnitureId() {
        return furniture.getFurnitureId();
    }

    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((furniture == null) ? 0 : furniture.hashCode());
        result = prime * result + (int) (member_id ^ (member_id >>> 32));
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
        MemberFurniture other = (MemberFurniture) obj;
        if (furniture == null) {
            if (other.furniture != null)
                return false;
        } else if (!furniture.equals(other.furniture))
            return false;
        if (member_id != other.member_id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MemberFurniture [member_id=%s, furniture=%s]", member_id, furniture);
    }
}
