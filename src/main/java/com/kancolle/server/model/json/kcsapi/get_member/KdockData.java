package com.kancolle.server.model.json.kcsapi.get_member;

import java.util.List;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.member.MemberKdock;

public class KdockData extends APIResponse<List<MemberKdock>> {
    @Override
    public KdockData setApi_data(List<MemberKdock> api_data) {
        super.setApi_data(api_data);
        return this;
    }
}
