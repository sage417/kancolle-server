package com.kancolle.server.controller.kcsapi;

import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kancolle.server.model.kcsapi.duty.DutyItemGetResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.duty.MemberDutyService;

@RestController
@RequestMapping(value = "/kcsapi/api_req_quest", method = RequestMethod.POST)
public class ReqQuestController {

    @Autowired
    private MemberDutyService memberDutySerivice;

    @RequestMapping("/start")
    public APIResponse<Object> start(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_quest_id") Integer quest_id) {
        memberDutySerivice.start(member_id, quest_id);
        return APIResponse.EMPTY_SUCCESS_RESPONSE;
    }

    @RequestMapping("/stop")
    public APIResponse<Object> stop(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_quest_id") Integer quest_id) {
        memberDutySerivice.stop(member_id, quest_id);
        return APIResponse.EMPTY_SUCCESS_RESPONSE;
    }

    @RequestMapping("/clearitemget")
    public APIResponse<DutyItemGetResult> clearitemget(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_quest_id") Integer quest_id) {
        DutyItemGetResult api_data = memberDutySerivice.clearitemget(member_id, quest_id);
        return new APIResponse<DutyItemGetResult>().setApi_data(api_data);
    }
}
