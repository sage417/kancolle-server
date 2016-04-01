/**
 *
 */
package com.kancolle.server.controller.kcsapi.battle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kancolle.server.controller.kcsapi.battle.form.BattleForm;
import com.kancolle.server.model.kcsapi.battle.BattleResult;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.battle.IBattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.google.common.base.Preconditions.checkArgument;
import static com.kancolle.server.controller.common.AdviceController.MEMBER_ID;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 */
@RestController
@RequestMapping(value = "/kcsapi/api_req_sortie", method = RequestMethod.POST)
public class ReqSortieController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IBattleService battleService;

    @RequestMapping("/battle")
    public APIResponse<BattleSimulationResult> battle(@ModelAttribute(MEMBER_ID) String member_id, @Validated BattleForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        BattleSimulationResult api_data = battleService.battle(member_id, form);
        return new APIResponse<BattleSimulationResult>().setApi_data(api_data);
    }

    @RequestMapping(value = "/battleresult", produces = "application/json; charset=UTF-8", method = RequestMethod.GET)
    public String battleresult(@ModelAttribute(MEMBER_ID) String member_id) throws JsonProcessingException {
        BattleResult api_data = battleService.battleresult(member_id);
        return objectMapper.writeValueAsString(new APIResponse<BattleResult>().setApi_data(api_data));
    }
}
