package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.web.interceptor.APITokenHandlerInterceptor.MEMBER_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kancolle.server.logic.DeckPortLogic;
import com.kancolle.server.service.member.MemberService;

@Controller
@RequestMapping(value = "/kcsapi/api_req_hensei", method = RequestMethod.POST)
public class ReqHenseiController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/change")
    public @ResponseBody String change(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam("api_id") int fleet_id, @RequestParam("api_ship_id") long ship_id,
            @RequestParam("api_ship_idx") int ship_idx) {
        if (fleet_id < 1 || fleet_id > 4 || ship_id < -2 || ship_idx < -1 || ship_idx > 5) {

        }
        if (DeckPortLogic.checkChange(fleet_id, ship_idx, ship_id))
            memberService.changeShip(member_id, fleet_id, ship_id, ship_idx);
        return "";
    }
}
