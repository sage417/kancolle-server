package com.kancolle.server.model.json.kcsapi.get_member;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.member.MemberBasic;

public class BasicData extends APIResponse<MemberBasic> {

	@Override
	public BasicData setApi_data(MemberBasic api_data) {
		super.setApi_data(api_data);
		return this;
	}
}
