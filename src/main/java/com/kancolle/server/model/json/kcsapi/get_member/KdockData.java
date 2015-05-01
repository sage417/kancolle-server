package com.kancolle.server.model.json.kcsapi.get_member;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.member.MemberKdock;

public class KdockData extends APIResponse<MemberKdock> {
	@Override
	public KdockData setApi_data(MemberKdock api_data) {
		super.setApi_data(api_data);
		return this;
	}
}
