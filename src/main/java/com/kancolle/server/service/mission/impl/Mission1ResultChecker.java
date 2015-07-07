/**
 * 
 */
package com.kancolle.server.service.mission.impl;

import java.util.function.Predicate;

import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.service.mission.MissionResultChecker;

/**
 * @author J.K.SAGE
 * @Date 2015年7月6日
 *
 */
public class Mission1ResultChecker extends MissionResultChecker {
    private static final Mission1ResultChecker checker = new Mission1ResultChecker();

    Predicate<MemberDeckPort> check = deckport -> deckport.getShips().size() > 1;

    private Mission1ResultChecker() {
    }

    public final static MissionResultChecker getInstance() {
        return checker;
    }

    @Override
    protected boolean checkCond(MemberDeckPort deckport) {
        return deckport.getShips().size() > 1;
    }
}
