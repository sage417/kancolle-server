package com.kancolle.server.model.json.kcsapi.req_member;

import java.util.Map;

import com.kancolle.server.model.json.APIResponse;

public class GetIncentiveData extends APIResponse<Map<String, Integer>> {

    @Override
    public GetIncentiveData setApi_data(Map<String, Integer> api_data) {
        super.setApi_data(api_data);
        return this;
    }
}
