package com.kancolle.server.modle.kcsapi.player;

public class PlayerSlotItem {

    private int api_id;

    private int api_slotitem_id;

    private int api_locked;

    private int api_level;

    public int getApi_id() {
        return api_id;
    }

    public void setApi_id(int api_id) {
        this.api_id = api_id;
    }

    public int getApi_slotitem_id() {
        return api_slotitem_id;
    }

    public void setApi_slotitem_id(int api_slotitem_id) {
        this.api_slotitem_id = api_slotitem_id;
    }

    public int getApi_locked() {
        return api_locked;
    }

    public void setApi_locked(int api_locked) {
        this.api_locked = api_locked;
    }

    public int getApi_level() {
        return api_level;
    }

    public void setApi_level(int api_level) {
        this.api_level = api_level;
    }
}
