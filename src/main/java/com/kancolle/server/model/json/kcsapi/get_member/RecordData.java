package com.kancolle.server.model.json.kcsapi.get_member;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.member.MemberRecord;

public class RecordData extends APIResponse<MemberRecord> {

    @Override
    public RecordData setApi_data(MemberRecord api_data) {
        super.setApi_data(api_data);
        return this;
    }
}
