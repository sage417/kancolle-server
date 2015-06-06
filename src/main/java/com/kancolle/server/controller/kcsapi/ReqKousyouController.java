package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kancolle.server.logic.ShipLogic;
import com.kancolle.server.service.member.MemberService;

@Controller
@RequestMapping("/kcsapi/api_req_kousyou")
public class ReqKousyouController {
    @Autowired
    private MemberService memberService;

    @ModelAttribute(MEMBER_ID)
    public String getMemberId(HttpServletRequest request) {
        return (String) request.getAttribute(MEMBER_ID);
    }

    public void destroyShip(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam("api_ship_id") long api_ship_id) {
        if (api_ship_id < 0L) {

        }
        if (ShipLogic.checkDestory(member_id, api_ship_id))
            memberService.destroyShip(member_id, api_ship_id);
    }
}
