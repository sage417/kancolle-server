package com.kancolle.server.model.kcsapi.slotitem;

public class MemberSlotItemLockResult {

    private int api_locked;

    public MemberSlotItemLockResult(boolean lock) {
        this.api_locked = lock ? 1 : 0;
    }

    public int getApi_locked() {
        return api_locked;
    }

    public void setApi_locked(int api_locked) {
        this.api_locked = api_locked;
    }
}
