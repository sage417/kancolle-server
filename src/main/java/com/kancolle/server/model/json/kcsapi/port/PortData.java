package com.kancolle.server.model.json.kcsapi.port;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.member.MemberPort;

public class PortData extends APIResponse<MemberPort> {

    @Override
    public PortData setApi_data(MemberPort api_data) {
        super.setApi_data(api_data);
        return this;
    }
}
