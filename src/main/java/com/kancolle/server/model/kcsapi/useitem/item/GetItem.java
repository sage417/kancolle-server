/**
 *
 */
package com.kancolle.server.model.kcsapi.useitem.item;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 */
@JsonPropertyOrder(value = {
        "api_usemst", "api_mst_id", "api_getcount"
})
public class GetItem {

    @JsonProperty(value = "api_usemst")
    @JSONField(ordinal = 1)
    private int api_usemst;

    @JsonProperty(value = "api_mst_id")
    @JSONField(ordinal = 2)
    private int api_mst_id;

    @JsonProperty(value = "api_getcount")
    @JSONField(ordinal = 3)
    private int api_getcount;

    public int getApi_usemst() {
        return api_usemst;
    }

    public GetItem() {
    }

    public GetItem(int api_usemst, int api_mst_id, int api_getcount) {
        this.api_usemst = api_usemst;
        this.api_mst_id = api_mst_id;
        this.api_getcount = api_getcount;
    }

    public void setApi_usemst(int api_usemst) {
        this.api_usemst = api_usemst;
    }

    public int getApi_mst_id() {
        return api_mst_id;
    }

    public void setApi_mst_id(int api_mst_id) {
        this.api_mst_id = api_mst_id;
    }

    public int getApi_getcount() {
        return api_getcount;
    }

    public void setApi_getcount(int api_getcount) {
        this.api_getcount = api_getcount;
    }
}
