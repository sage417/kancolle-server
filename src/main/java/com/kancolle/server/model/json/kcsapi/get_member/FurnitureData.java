package com.kancolle.server.model.json.kcsapi.get_member;

import java.util.List;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;

public class FurnitureData extends APIResponse<List<MemberFurniture>> {
	@Override
	public FurnitureData setApi_data(List<MemberFurniture> api_data) {
		super.setApi_data(api_data);
		return this;
	}
}
