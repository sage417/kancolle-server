package com.kancolle.server.model.kcsapi.ship;

public class MemberShipLockResult {

    private int api_locked;

    public MemberShipLockResult(boolean lock) {
        this.api_locked = lock ? 1 : 0;
    }

    public int getApi_locked() {
        return api_locked;
    }

    public void setApi_locked(int api_locked) {
        this.api_locked = api_locked;
    }
}
