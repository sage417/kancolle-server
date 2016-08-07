/**
 *
 */
package com.kancolle.server.model.kcsapi.misson;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.useitem.UseItem;

/**
 * @author J.K.SAGE
 * @Date 2015年7月9日
 */
@JsonPropertyOrder(value = {
        "api_useitem_id", "api_useitem_name", "api_useitem_count"
})
public class GetUseItem {

    @JsonProperty(value = "api_useitem_id")
    @JSONField(ordinal = 1)
    private int api_useitem_id;

    @JsonProperty(value = "api_useitem_name")
    @JSONField(ordinal = 2)
    private String api_useitem_name;

    @JsonProperty(value = "api_useitem_count")
    @JSONField(ordinal = 3)
    private int api_useitem_count;

    public GetUseItem(UseItem useItem, int api_useitem_count) {
        super();
        this.api_useitem_id = useItem.getUseitemId();
        this.api_useitem_name = useItem.getName();
        this.api_useitem_count = api_useitem_count;
    }

    public int getApi_useitem_id() {
        return api_useitem_id;
    }

    public void setApi_useitem_id(int api_useitem_id) {
        this.api_useitem_id = api_useitem_id;
    }

    public String getApi_useitem_name() {
        return api_useitem_name;
    }

    public void setApi_useitem_name(String api_useitem_name) {
        this.api_useitem_name = api_useitem_name;
    }

    public int getApi_useitem_count() {
        return api_useitem_count;
    }

    public void setApi_useitem_count(int api_useitem_count) {
        this.api_useitem_count = api_useitem_count;
    }
}
