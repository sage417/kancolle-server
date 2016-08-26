package com.kancolle.server.model.po.furniture;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "member_id", "api_id", "api_furniture_type", "api_furniture_no",
        "api_furniture_id"
})
@Alias("MemberFurniture")
public class MemberFurniture implements Serializable {

    private static final long serialVersionUID = -4139710204648199838L;

    @JsonProperty(value = "api_member_id")
    @JSONField(ordinal = 1, name = "api_member_id")
    private long member_id;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private Furniture furniture;

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 2, name = "api_id")
    public int returnId() {
        return furniture.getFurnitureId();
    }

    @JsonProperty(value = "api_furniture_type")
    @JSONField(ordinal = 3, name = "api_furniture_type")
    public int returnFurnitureType() {
        return furniture.getType();
    }

    @JsonProperty(value = "api_furniture_no")
    @JSONField(ordinal = 4, name = "api_furniture_no")
    public int returnFurniureNo() {
        return furniture.getNo();
    }

    @JsonProperty(value = "api_furniture_id")
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
