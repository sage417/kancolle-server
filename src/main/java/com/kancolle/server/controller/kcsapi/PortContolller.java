package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.member.MemberService;

@RestController
@RequestMapping(value = "/kcsapi/api_port", method = RequestMethod.POST)
public class PortContolller {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/port")
    public APIResponse<MemberPort> port(@ModelAttribute(MEMBER_ID) String member_id) throws Exception {
        MemberPort api_data = memberService.getPort(member_id);
        return new APIResponse<MemberPort>().setApi_data(api_data);
    }
}
