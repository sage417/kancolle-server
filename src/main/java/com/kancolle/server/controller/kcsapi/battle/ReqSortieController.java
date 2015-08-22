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

import com.kancolle.server.controller.kcsapi.battle.form.BattleForm;
import com.kancolle.server.model.kcsapi.battle.BattleResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.battle.BattleService;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
@RestController
@RequestMapping(value = "/kcsapi/api_req_sortie", method = RequestMethod.POST)
public class ReqSortieController {
    @Autowired
    private BattleService battleService;

    @RequestMapping("battle")
    public APIResponse<BattleResult> battle(@ModelAttribute(MEMBER_ID) String member_id, @Valid BattleForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        BattleResult api_data = battleService.battle(member_id, form);
        return new APIResponse<BattleResult>().setApi_data(api_data);
    }

}
