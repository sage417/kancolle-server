package com.kancolle.server.model.json.kcsapi;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.start.StartModel;

public class StartData extends APIResponse<StartModel> {

	@Override
	public StartData setApi_data(StartModel api_data) {
		super.setApi_data(api_data);
		return this;
	}
}
