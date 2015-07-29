/**
 * 
 */
package com.kancolle.server.service.mission.impl;

import org.springframework.stereotype.Component;

import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.service.mission.MissionResultChecker;

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
