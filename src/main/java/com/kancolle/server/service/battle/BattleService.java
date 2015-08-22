/**
 * 
 */
package com.kancolle.server.service.battle;

import com.kancolle.server.controller.kcsapi.battle.form.BattleForm;
import com.kancolle.server.model.kcsapi.battle.BattleResult;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
public interface BattleService {

    BattleResult battle(String member_id, BattleForm form);

}
