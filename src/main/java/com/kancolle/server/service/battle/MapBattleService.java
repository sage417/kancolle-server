/**
 * 
 */
package com.kancolle.server.service.battle;

import com.kancolle.server.controller.kcsapi.battle.form.MapStartForm;
import com.kancolle.server.model.kcsapi.battle.MapStartResult;

/**
 * @author J.K.SAGE
 * @Date 2015年8月20日
 *
 */
public interface MapBattleService {

    MapStartResult start(String member_id, MapStartForm form);

}
