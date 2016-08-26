/**
 * 
 */
package com.kancolle.server.service.mission.resultchecker;

import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.service.mission.MissionResultChecker;
import org.springframework.stereotype.Component;

/**
 * @author J.K.SAGE
 * @Date 2015年7月6日
 *
 */
@Component
public class Mission1ResultChecker implements MissionResultChecker {

    @Override
    public boolean checkCond(MemberDeckPort deckport) {
        return deckport.getShips().size() > 1;
    }
}
