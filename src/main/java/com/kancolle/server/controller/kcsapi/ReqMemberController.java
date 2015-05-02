package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.model.json.kcsapi.req_member.GetIncentiveData;

@Controller
@RequestMapping("/kcsapi/api_req_member")
public class ReqMemberController {
    private static final GetIncentiveData svdata = new GetIncentiveData().setApi_data(Collections.singletonMap("api_count", 0));

    @ModelAttribute(MEMBER_ID)
    public String getMemberId(HttpServletRequest request) {
        return (String) request.getAttribute(MEMBER_ID);
    }

    @RequestMapping("get_incentive")
    public @ResponseBody GetIncentiveData getIncentive() {
        return svdata;
    }
}
