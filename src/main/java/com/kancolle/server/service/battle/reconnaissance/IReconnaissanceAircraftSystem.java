/**
 * 
 */
package com.kancolle.server.service.battle.reconnaissance;

import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.AbstractShip;

/**
 * @author J.K.SAGE
 * @Date 2015年8月24日
 *
 */
public interface IReconnaissanceAircraftSystem {

    int memberDeckPortSearchEnemy(MemberDeckPort deckport, EnemyDeckPort enemyDeckPort, int aerialState);

    int enemyDeckPortSearchMember(MemberDeckPort memberDeckPort, EnemyDeckPort enemyDeckPort);

    boolean isSearchSuccess(int fsResult);

    int getShipSearchValue(AbstractShip ship);
}
