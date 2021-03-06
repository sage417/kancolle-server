package com.kancolle.server.model.po.useitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("UseItem")
@JsonPropertyOrder(value = {
        "useitemId", "usetype", "category", "name",
        "description", "price"
})
public class UseItem implements Serializable {

    private static final long serialVersionUID = -138311826015722610L;

    @JsonProperty(value = "api_id")
    private int useitemId;

    @JsonProperty(value = "api_usetype")
    private int usetype;

    @JsonProperty(value = "api_category")
    private int category;

    @JsonProperty(value = "api_name")
    private String name;

    @JsonProperty(value = "api_description")
    private ImmutableList<String> description;

    @JsonProperty(value = "api_price")
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

    public ImmutableList<String> getDescription() {
        return description;
    }

    public void setDescription(ImmutableList<String> description) {
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
