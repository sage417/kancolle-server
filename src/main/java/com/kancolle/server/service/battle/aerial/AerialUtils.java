/**
 * 
 */
package com.kancolle.server.service.battle.aerial;

import static com.kancolle.server.service.battle.aerial.AerialBattleSystem.AIR_BATTLE_ADVANTAGE;
import static com.kancolle.server.service.battle.aerial.AerialBattleSystem.AIR_BATTLE_GUARANTEE;

import com.kancolle.server.model.kcsapi.battle.houku.KouKuResult;

/**
 * @author J.K.SAGE
 * @Date 2015年11月14日
 *
 */
public class AerialUtils {

    /**
     * @param kouKuResult
     * @return
     */
    public static boolean testAerialAdvence(KouKuResult kouKuResult) {
        if (kouKuResult == null)
            return false;

        int aerialStatue = kouKuResult.getStage1().getApi_disp_seiku();
        return aerialStatue == AIR_BATTLE_GUARANTEE || aerialStatue == AIR_BATTLE_ADVANTAGE;
    }
}
