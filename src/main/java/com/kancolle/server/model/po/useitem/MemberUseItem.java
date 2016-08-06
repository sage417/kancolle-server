package com.kancolle.server.model.po.useitem;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@JsonPropertyOrder(value = {
        "member_id", "useItemId", "value", "usetype",
        "category", "name", "description", "price",
        "count"
})
@Alias("MemberUseItem")
public class MemberUseItem implements Serializable {

    private static final long serialVersionUID = 3773472417961605052L;

    @JsonProperty(value = "api_member_id")
    @JSONField(ordinal = 1, name = "api_member_id")
    private long member_id;

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 2, name = "api_id")
    private int useItemId;

    @JsonProperty(value = "api_value")
    @JSONField(ordinal = 3, name = "api_value")
    public int value;

    @JsonProperty(value = "api_usetype")
    @JSONField(ordinal = 4, name = "api_usetype")
    private int usetype;

    @JsonProperty(value = "api_category")
    @JSONField(ordinal = 5, name = "api_category")
    private int category;

    @JsonProperty(value = "api_name")
    @JSONField(ordinal = 6, name = "api_name")
    private String name;

    @JsonProperty(value = "api_description")
    @JSONField(ordinal = 7, name = "api_description")
    private JSONArray description;

    @JsonProperty(value = "api_price")
    @JSONField(ordinal = 8, name = "api_price")
    private int price;

    @JsonProperty(value = "api_count")
    @JSONField(ordinal = 9, name = "api_count")
    private int count;

    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public int getUseItemId() {
        return useItemId;
    }

    public void setUseItemId(int useItemId) {
        this.useItemId = useItemId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getUsetype() {
        return usetype;
    }

    public void setUsetype(int usetype) {
        this.usetype = usetype;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONArray getDescription() {
        return description;
    }

    public void setDescription(JSONArray description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (member_id ^ (member_id >>> 32));
        result = prime * result + useItemId;
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
        MemberUseItem other = (MemberUseItem) obj;
        if (member_id != other.member_id)
            return false;
        if (useItemId != other.useItemId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MemberUseItem [member_id=" + member_id + ", useItemId=" + useItemId + ", count=" + count + "]";
    }
}
