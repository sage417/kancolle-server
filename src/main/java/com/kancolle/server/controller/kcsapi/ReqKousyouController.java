package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kancolle.server.logic.ShipLogic;
import com.kancolle.server.service.member.MemberService;

@RestController
@RequestMapping(value = "/kcsapi/api_req_kousyou", method = RequestMethod.POST)
public class ReqKousyouController {
    @Autowired
    private MemberService memberService;

    public void destroyShip(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam("api_ship_id") long api_ship_id) {
        if (api_ship_id < 0L) {

        }
        if (ShipLogic.checkDestory(member_id, api_ship_id))
            ;
    }
}
