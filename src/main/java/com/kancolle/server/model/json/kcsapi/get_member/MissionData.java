package com.kancolle.server.model.json.kcsapi.get_member;

import java.util.List;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.member.MemberMission;

public class MissionData extends APIResponse<List<MemberMission>> {

    @Override
    public MissionData setApi_data(List<MemberMission> api_data) {
        super.setApi_data(api_data);
        return this;
    }
}
