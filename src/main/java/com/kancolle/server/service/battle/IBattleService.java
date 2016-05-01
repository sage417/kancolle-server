/**
 * 
 */
package com.kancolle.server.service.battle;

import com.kancolle.server.controller.kcsapi.battle.form.BattleForm;
import com.kancolle.server.model.kcsapi.battle.BattleResult;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
public interface IBattleService {

    BattleSimulationResult battle(String member_id, BattleForm form);

    BattleResult battleresult(String member_id);

    void updateAfterBattleResult(String member_id);
}
