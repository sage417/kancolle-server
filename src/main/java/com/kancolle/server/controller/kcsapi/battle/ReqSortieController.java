/**
 *
 */
package com.kancolle.server.controller.kcsapi.battle;

import com.kancolle.server.controller.kcsapi.battle.form.BattleForm;
import com.kancolle.server.model.kcsapi.battle.BattleResult;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.response.APIResponse;
import com.kancolle.server.service.battle.BattleService;
import io.reactivex.Single;
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
 *
 */
@RestController
@RequestMapping(value = "/kcsapi/api_req_sortie", method = RequestMethod.POST)
public class ReqSortieController {
    @Autowired
    private BattleService battleService;

    @RequestMapping("/battle")
    public Single<APIResponse<BattleSimulationResult>> battle(@ModelAttribute(MEMBER_ID) long member_id, @Validated BattleForm form, BindingResult result) {
        checkArgument(!result.hasErrors());
        return battleService.battleSingle(member_id, form);
    }

    @RequestMapping("/battleresult")
    public APIResponse<BattleResult> battleResult(@ModelAttribute(MEMBER_ID) long member_id) {
        BattleResult api_data = battleService.battleresult(member_id);
        return new APIResponse<BattleResult>().setApi_data(api_data);
    }
}
