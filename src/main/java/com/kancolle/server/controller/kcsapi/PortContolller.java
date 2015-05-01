package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.model.json.kcsapi.port.PortData;

@Controller
@RequestMapping("/kcsapi/api_port")
public class PortContolller {

	@ModelAttribute(MEMBER_ID)
	public String getMemberId(HttpServletRequest request) {
		return (String) request.getAttribute(MEMBER_ID);
	}

	@RequestMapping("/port")
	public @ResponseBody PortData port() {
		return new PortData();
	}
}
