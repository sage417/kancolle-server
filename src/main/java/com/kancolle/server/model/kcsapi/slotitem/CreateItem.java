/**
 * 
 */
package com.kancolle.server.model.kcsapi.slotitem;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年7月12日
 *
 */
public class CreateItem {

    @JSONField(ordinal = 1)
    private long api_id;

    @JSONField(ordinal = 2)
    private int api_slotitem_id;

    public CreateItem(long api_id, int api_slotitem_id) {
        super();
        this.api_id = api_id;
        this.api_slotitem_id = api_slotitem_id;
    }

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
