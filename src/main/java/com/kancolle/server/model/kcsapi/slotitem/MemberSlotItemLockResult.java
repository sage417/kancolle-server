package com.kancolle.server.model.kcsapi.slotitem;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kancolle.server.utils.jackson.NumericBooleanSerializer;

public class MemberSlotItemLockResult {

    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean api_locked;

    public MemberSlotItemLockResult(boolean lock) {
        this.api_locked = lock;
    }

    public boolean getApi_locked() {
        return api_locked;
    }

    public void setApi_locked(boolean api_locked) {
        this.api_locked = api_locked;
    }
}
