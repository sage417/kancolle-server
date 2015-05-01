package com.kancolle.server.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kancolle.server.model.json.APIResponse;

@Controller
@RequestMapping("/common")
public class CommonController {

	@RequestMapping("/tokenerror")
	public @ResponseBody String badRequest() {
		return JSON.toJSONString(new APIResponse<Object>(), SerializerFeature.BrowserCompatible);
	}
}
