/**
 * 
 */
package com.kancolle.server.service.battle.reconnaissance;

import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;

/**
 * @author J.K.SAGE
 * @Date 2015年8月24日
 *
 */
public interface IReconnaissanceAircraftSystem {

    int memberDeckPortSearchEnemy(MemberDeckPort deckport, UnderSeaDeckPort underSeaDeckPort, int aerialState);

    int enemyDeckPortSearchMember(MemberDeckPort memberDeckPort, UnderSeaDeckPort underSeaDeckPort);

    boolean isSearchSuccess(int fsResult);
}
