package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.duty.MemberDutyService;

@RestController
@RequestMapping(value = "/kcsapi/api_req_quest", method = RequestMethod.POST)
public class ReqQuestController {
    private static final APIResponse<Object> DEFAULT_RESPONSE = new APIResponse<>();

    @Autowired
    private MemberDutyService memberDutySerivice;

    @RequestMapping("/start")
    public APIResponse<?> start(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_quest_id") Integer quest_id) {
        memberDutySerivice.start(member_id, quest_id);
        return DEFAULT_RESPONSE;
    }

    @RequestMapping("/stop")
    public APIResponse<?> stop(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_quest_id") Integer quest_id) {
        memberDutySerivice.stop(member_id, quest_id);
        return DEFAULT_RESPONSE;
    }

}
