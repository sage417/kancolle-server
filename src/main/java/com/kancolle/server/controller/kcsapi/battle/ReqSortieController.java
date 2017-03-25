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
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    private BattleService battleService;

    private final Executor executor = new ThreadPoolExecutor(20, 200, 60L, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(400), new ThreadPoolExecutor.AbortPolicy());

    @RequestMapping("/battle")
    public Single<APIResponse<BattleSimulationResult>> battle(@ModelAttribute(MEMBER_ID) final long member_id, @Validated final BattleForm form, final BindingResult result) {
        checkArgument(!result.hasErrors());
        return Single
                .create((SingleOnSubscribe<APIResponse<BattleSimulationResult>>) e ->
                        e.onSuccess(APIResponse.<BattleSimulationResult>builder().data(battleService.battle(member_id, form)).build()))
                .subscribeOn(Schedulers.from(executor));
    }

    @RequestMapping("/battleresult")
    public APIResponse<BattleResult> battleResult(@ModelAttribute(MEMBER_ID) long member_id) {
        BattleResult api_data = battleService.battleresult(member_id);
        return new APIResponse<BattleResult>().setApi_data(api_data);
    }
}
