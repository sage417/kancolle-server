package com.kancolle.server.model.po.useitem;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;

@Alias("UseItem")
public class UseItem implements Serializable {

    private static final long serialVersionUID = -138311826015722610L;

    @JSONField(ordinal = 1, name = "api_id")
    private int useitemId;

    @JSONField(ordinal = 2, name = "api_usetype")
    private int usetype;

    @JSONField(ordinal = 3, name = "api_category")
    private int category;

    @JSONField(ordinal = 4, name = "api_name")
    private String name;

    @JSONField(ordinal = 5, name = "api_description")
    private JSONArray description;

    @JSONField(ordinal = 6, name = "api_price")
    private int price;

    public int getUseitemId() {
        return useitemId;
    }

    public void setUseitemId(int useitemId) {
        this.useitemId = useitemId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + useitemId;
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
        UseItem other = (UseItem) obj;
        if (useitemId != other.useitemId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("UseItem [name=%s]", name);
    }
}
