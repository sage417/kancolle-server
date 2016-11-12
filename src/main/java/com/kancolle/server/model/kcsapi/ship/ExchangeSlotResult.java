package com.kancolle.server.model.kcsapi.ship;

import java.util.Arrays;

/**
 * Created by SAGE on 2016/10/4.
 */
public class ExchangeSlotResult {

    private long[] api_slot;

    public ExchangeSlotResult(long[] slot) {
        this.api_slot = Arrays.copyOf(slot, slot.length);
    }

    public long[] getApi_slot() {
        return api_slot;
    }

    public void setApi_slot(long[] api_slot) {
        this.api_slot = api_slot;
    }
}
