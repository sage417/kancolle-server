package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.model.json.APIResponse;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.service.member.MemberService;

@Controller
@RequestMapping("/kcsapi/api_port")
public class PortContolller {
    @Autowired
    private MemberService memberService;

    @ModelAttribute(MEMBER_ID)
    public String getMemberId(HttpServletRequest request) {
        return (String) request.getAttribute(MEMBER_ID);
    }

    @RequestMapping("/port")
    public @ResponseBody APIResponse<MemberPort> port(@ModelAttribute(MEMBER_ID) String member_id) throws Exception {
        MemberPort api_data = memberService.getPort(member_id);
        return new APIResponse<MemberPort>().setApi_data(api_data);
    }
}
