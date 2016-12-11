package com.kancolle.server.controller.kcsapi;

import com.kancolle.server.controller.kcsapi.form.mission.MissionStartForm;
import com.kancolle.server.model.kcsapi.misson.MissionResult;
import com.kancolle.server.model.kcsapi.misson.MissionReturn;
import com.kancolle.server.model.kcsapi.misson.MissionStart;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.mission.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

@RestController
@RequestMapping(value = "/kcsapi/api_req_mission", method = RequestMethod.POST)
public class ReqMissionController {

    @Autowired
    private MissionService missionService;

    @RequestMapping("/result")
    public APIResponse<?> result(@ModelAttribute(MEMBER_ID) long member_id, @RequestParam(value = "api_deck_id", required = true) Integer api_deck_id) {
        if (api_deck_id < 2) {
            // TODO 惡意請求記錄
            return new APIResponse<String>();
        }

        MissionResult api_data = missionService.calMissionResult(member_id, api_deck_id);
        return new APIResponse<MissionResult>().setApi_data(api_data);
    }

    @RequestMapping("/return_instruction")
    public APIResponse<?> return_instruction(@ModelAttribute(MEMBER_ID) long member_id, int api_deck_id) {
        if (api_deck_id < 2) {
            // TODO 惡意請求記錄
            return new APIResponse<String>();
        }

        MissionReturn api_data = missionService.callbackMission(member_id, api_deck_id);
        return new APIResponse<MissionReturn>().setApi_data(api_data);
    }

    @RequestMapping("/start")
    public APIResponse<?> start(@ModelAttribute(MEMBER_ID) long member_id, @Validated MissionStartForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        MissionStart api_data = missionService.startMission(member_id, form);
        return new APIResponse<MissionStart>().setApi_data(api_data);
    }
}
