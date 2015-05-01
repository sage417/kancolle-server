package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.model.json.kcsapi.req_member.GetIncentiveData;

@Controller
@RequestMapping("/kcsapi/api_req_member")
public class ReqMemberController {
	
	@ModelAttribute(MEMBER_ID)
	public String getMemberId(HttpServletRequest request) {
		return (String) request.getAttribute(MEMBER_ID);
	}

    @RequestMapping("get_incentive")
    public @ResponseBody GetIncentiveData getIncentive() {
        return new GetIncentiveData();
    }

}
