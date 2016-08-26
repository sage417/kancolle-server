/**
 *
 */
package com.kancolle.server.controller.kcsapi.battle;

import com.kancolle.server.controller.kcsapi.battle.form.MapStartForm;
import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.battle.map.MapBattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

/**
 * @author J.K.SAGE
 * @Date 2015年8月20日
 *
 */
@RestController
@RequestMapping(value = "/kcsapi/api_req_map", method = RequestMethod.POST)
public class ReqMapController {

    @Autowired
    private MapBattleService mapBattleService;

    @RequestMapping("/start")
    public APIResponse<MapStartResult> start(@ModelAttribute(MEMBER_ID) String member_id, @Validated MapStartForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        MapStartResult api_data = mapBattleService.start(member_id, form);
        return new APIResponse<MapStartResult>().setApi_data(api_data);
    }

    @RequestMapping("/next")
    public APIResponse<MapNextResult> next(@ModelAttribute(MEMBER_ID) String member_id, @RequestParam(value = "api_recovery_type", defaultValue = "0") int recoverType)  {
        MapNextResult api_data = mapBattleService.next(member_id, recoverType);
        return new APIResponse<MapNextResult>().setApi_data(api_data);
    }
}
