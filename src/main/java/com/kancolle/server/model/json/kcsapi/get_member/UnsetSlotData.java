package com.kancolle.server.model.json.kcsapi.get_member;

import java.util.Map;

import com.kancolle.server.model.json.APIResponse;

public class UnsetSlotData extends APIResponse<Map<String, Object>> {
    @Override
    public UnsetSlotData setApi_data(Map<String, Object> api_data) {
        super.setApi_data(api_data);
        return this;
    }
}
