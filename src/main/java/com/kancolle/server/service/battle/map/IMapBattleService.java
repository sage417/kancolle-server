/**
 * 
 */
package com.kancolle.server.service.battle.map;

import com.kancolle.server.controller.kcsapi.battle.form.MapStartForm;
import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;

/**
 * @author J.K.SAGE
 * @Date 2015年8月20日
 *
 */
public interface IMapBattleService {

    MapStartResult start(String member_id, MapStartForm form);

    MapStartResult next(String member_id, int recoverType);
}
