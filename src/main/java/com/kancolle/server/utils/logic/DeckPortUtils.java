/**
 * 
 */
package com.kancolle.server.utils.logic;

import static com.kancolle.server.service.battle.impl.AerialBattleSystemImpl.AIR_BATTLE_DISADVANTAGE;
import static com.kancolle.server.service.battle.impl.AerialBattleSystemImpl.AIR_BATTLE_LOST;
import static com.kancolle.server.utils.logic.ship.ShipFilter.antiSSShipFilter;
import static com.kancolle.server.utils.logic.ship.ShipFilter.carrierHasAtackPlaneFilter;
import static com.kancolle.server.utils.logic.ship.ShipFilter.ssFilter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomUtils;

import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.ShipType;

/**
 * @author J.K.SAGE
 * @Date 2015年8月23日
 *
 */
public class DeckPortUtils {

    private DeckPortUtils() {
    }

    private static int getShipSearchNeedValue(int shipType) {
        switch (shipType) {
        case ShipType.TYPE_DD:
            return 1;
        case ShipType.TYPE_SS:
        case ShipType.TYPE_CL:
        case ShipType.TYPE_CLT:
            return 2;
        case ShipType.TYPE_CA:
        case ShipType.TYPE_CAV:
            return 3;
        case ShipType.TYPE_CVL:
            return 4;
        case ShipType.TYPE_BB1:
        case ShipType.TYPE_BB2:
        case ShipType.TYPE_BBS:
        case ShipType.TYPE_BBV:
            return 5;
        case ShipType.TYPE_CV:
            return 6;
        default:
            return 1;
        }
    }

    /**
     * @param memberDeckPort
     * @return
     */
    public static int calMemberDeckPortSearchMinValue(MemberDeckPort memberDeckPort) {
        List<MemberShip> ships = memberDeckPort.getShips();
        if (ships.isEmpty()) {
            return 0;
        }
        int needValue = 0;
        for (MemberShip ship : ships) {
            needValue += getShipSearchNeedValue(ship.getShip().getType().getShipTypeId());
        }
        return needValue / ships.size();
    }

    public static int calEnemyDeckPortSearchMinValue(EnemyDeckPort enemyDeckPort) {
        List<EnemyShip> ships = enemyDeckPort.getEnemyShips();
        if (ships.isEmpty()) {
            return 0;
        }
        int needValue = 0;
        for (EnemyShip ship : ships) {
            needValue += getShipSearchNeedValue(ship.getShip().getShipTypeId());
        }
        return needValue / ships.size();
    }

    public static boolean attackAirSearchPlane(int aerialState) {
        if (aerialState == AIR_BATTLE_DISADVANTAGE) {
            // 20%
            return RandomUtils.nextInt(0, 5) > 1;
        }
        if (aerialState == AIR_BATTLE_LOST) {
            // 40%
            return RandomUtils.nextInt(0, 5) > 2;
        }
        return false;
        // return attackValue == 0 || (Math.atan(attackValue / 500) / Math.PI *
        // 200) < RandomUtils.nextInt(0, 101);
    }

    public static <T extends AbstractShip> List<T> getAttackShips(List<T> ships, boolean isAllSS) {
        //潜艇不能参加炮击战
        Stream<T> shipStream = ships.stream().filter(ship -> ssFilter.negate().test(ship));
        //没有搭载攻击机的空母不能参加炮击战
        shipStream.filter(ship -> carrierHasAtackPlaneFilter.test(ship));

        //如果全是潜艇，则所有非反潜船不能参加
        if (isAllSS) {
            shipStream.filter(ship -> antiSSShipFilter.test(ship)).collect(Collectors.toList());
        }
        return shipStream.collect(Collectors.toList());
    }
}
