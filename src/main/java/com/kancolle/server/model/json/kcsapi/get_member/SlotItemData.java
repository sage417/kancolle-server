package com.kancolle.server.model.json.kcsapi.get_member;

import java.util.List;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;

public class SlotItemData extends APIResponse<List<MemberSlotItem>> {
    @Override
    public SlotItemData setApi_data(List<MemberSlotItem> api_data) {
        super.setApi_data(api_data);
        return this;
    }
}
