/**
 * 
 */
package com.kancolle.server.controller.kcsapi.battle;

import static com.google.common.base.Preconditions.checkArgument;
import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kancolle.server.controller.kcsapi.battle.form.MapStartForm;
import com.kancolle.server.model.kcsapi.battle.MapStartResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.battle.MapBattleService;

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
    public APIResponse<MapStartResult> start(@ModelAttribute(MEMBER_ID) String member_id, @Valid MapStartForm form, BindingResult result) {
        checkArgument(!result.hasErrors());

        MapStartResult api_data = mapBattleService.start(member_id, form);

        return new APIResponse<MapStartResult>().setApi_data(api_data);
    }

}
