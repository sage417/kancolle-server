package com.kancolle.server.model.po.useitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("MemberUseItem")
@JsonPropertyOrder(value = {
        "member_id", "useItemId", "value", "api_usetype",
        "api_category", "api_name", "description", "api_price",
        "count"
})
public class MemberUseItem implements Serializable {

    private static final long serialVersionUID = 3773472417961605052L;

    @JsonProperty(value = "api_member_id")
    private final long member_id;

    @JsonProperty(value = "api_id")
    private final int useItemId;

    @JsonProperty(value = "api_value")
    public final int value;

    @JsonIgnore
    private final UseItem useItem;

    @JsonProperty(value = "api_description")
    private final ImmutableList<String> description = ImmutableList.of("", "");

    @JsonProperty(value = "api_count")
    private int count;

    public MemberUseItem(final long member_id, final int useItemId, final int value, final UseItem useItem) {
        this.member_id = member_id;
        this.useItemId = useItemId;
        this.value = value;
        this.useItem = useItem;
    }

    public long getMember_id() {
        return member_id;
    }

    public int getUseItemId() {
        return useItemId;
    }

    public int getValue() {
        return value;
    }

    public UseItem getUseItem() {
        return useItem;
    }

    public ImmutableList<String> getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @JsonProperty(value = "api_usetype")
    public int getUsetype() {
        return useItem.getUsetype();
    }

    @JsonProperty(value = "api_category")
    public int getCategory() {
        return useItem.getCategory();
    }

    @JsonProperty(value = "api_name")
    public String getName() {
        return useItem.getName();
    }

    @JsonProperty(value = "api_price")
    public int getPrice() {
        return useItem.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberUseItem that = (MemberUseItem) o;

        if (member_id != that.member_id) return false;
        if (useItemId != that.useItemId) return false;
        if (value != that.value) return false;
        return count == that.count;

    }

    @Override
    public int hashCode() {
        int result = (int) (member_id ^ (member_id >>> 32));
        result = 31 * result + useItemId;
        result = 31 * result + value;
        result = 31 * result + count;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MemberUseItem{");
        sb.append("member_id=").append(member_id);
        sb.append(", useItem=").append(useItem);
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}
