package com.kancolle.server.model.json.kcsapi.get_member;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;

public class SlotItemData extends APIResponse<MemberSlotItem> {
	@Override
	public SlotItemData setApi_data(MemberSlotItem api_data) {
		super.setApi_data(api_data);
		return this;
	}
}
