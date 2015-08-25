/**
 * 
 */
package com.kancolle.server.service.battle;

import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.model.po.deckport.MemberDeckPort;

/**
 * @author J.K.SAGE
 * @Date 2015年8月24日
 *
 */
public interface ReconnaissanceAircraftSystem {

    int memberDeckPortSearchEnemy(MemberDeckPort deckport, EnemyDeckPort enemyDeckPort);

}
