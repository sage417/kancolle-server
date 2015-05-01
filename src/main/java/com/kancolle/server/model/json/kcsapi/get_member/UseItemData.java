package com.kancolle.server.model.json.kcsapi.get_member;

import java.util.List;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;

public class UseItemData extends APIResponse<List<MemberUseItem>> {
    @Override
    public UseItemData setApi_data(List<MemberUseItem> api_data) {
        super.setApi_data(api_data);
        return this;
    }
}
