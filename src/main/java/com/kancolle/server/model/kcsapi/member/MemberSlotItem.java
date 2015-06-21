package com.kancolle.server.model.kcsapi.member;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.dao.annotation.Column;

public class MemberSlotItem {

    @JSONField(ordinal = 1)
    private long api_id;

    @JSONField(ordinal = 2)
    private int api_slotitem_id;

    @JSONField(ordinal = 3)
    private int api_locked;

    @JSONField(ordinal = 4)
    private int api_level;

    public long getApi_id() {
        return api_id;
    }

    public int getApi_level() {
        return api_level;
    }

    public int getApi_locked() {
        return api_locked;
    }

    public int getApi_slotitem_id() {
        return api_slotitem_id;
    }

    @Column(name = "ID", type = long.class)
    public void setApi_id(long api_id) {
        this.api_id = api_id;
    }

    @Column(name = "LEVEL", type = int.class)
    public void setApi_level(int api_level) {
        this.api_level = api_level;
    }

    @Column(name = "LOCKED", type = int.class)
    public void setApi_locked(int api_locked) {
        this.api_locked = api_locked;
    }

    @Column(name = "SLOTITEM_ID", type = int.class)
    public void setApi_slotitem_id(int api_slotitem_id) {
        this.api_slotitem_id = api_slotitem_id;
    }
}
