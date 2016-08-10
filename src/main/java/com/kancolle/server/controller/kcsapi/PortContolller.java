package com.kancolle.server.controller.kcsapi;

import com.fasterxml.jackson.annotation.JsonView;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.battle.IBattleService;
import com.kancolle.server.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

@RestController
@RequestMapping(value = "/kcsapi/api_port", method = RequestMethod.POST)
public class PortContolller {
    @Autowired
    private MemberService memberService;
    @Autowired
    private IBattleService battleService;

    @JsonView(Member.PortView.class)
    @RequestMapping("/port")
    public APIResponse<MemberPort> port(@ModelAttribute(MEMBER_ID) String member_id) throws Exception {
        battleService.updateAfterBattleResult(member_id);
        MemberPort api_data = memberService.getPort(member_id);
        return new APIResponse<MemberPort>().setApi_data(api_data);
    }
}
