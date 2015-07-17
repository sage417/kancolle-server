package com.kancolle.server.model.kcsapi.kcock;

import com.alibaba.fastjson.annotation.JSONField;

public class GetShipSlotItem {

    @JSONField(ordinal = 1)
    private long api_id;

    @JSONField(ordinal = 2)
    private int api_slotitem_id;

    public long getApi_id() {
        return api_id;
    }

    public void setApi_id(long api_id) {
        this.api_id = api_id;
    }

    public int getApi_slotitem_id() {
        return api_slotitem_id;
    }

    public void setApi_slotitem_id(int api_slotitem_id) {
        this.api_slotitem_id = api_slotitem_id;
    }

}
