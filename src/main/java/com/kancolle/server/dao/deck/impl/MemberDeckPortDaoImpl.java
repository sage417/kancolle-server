package com.kancolle.server.dao.deck.impl;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.deck.MemberDeckPortDao;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MemberDeckPortDaoImpl extends BaseDaoImpl<MemberDeckPort> implements MemberDeckPortDao {

    public static final String UPDATE_COLUMN_SHIP = "ship";
    public static final String UPDATE_COLUMN_MISSION_STATUS = "mission_status";
    public static final String UPDATE_COLUMN_MISSION_ID = "mission_id";
    public static final String UPDATE_COLUMN_MISSION_COMPLETE_TIME = "mission_complete_time";
    public static final String UPDATE_COLUMN_MISSION_FLAG = "mission_flag";
    public static final String UPDATE_COLUMN_LOCK = "lock";

    @Override
    public void update(MemberDeckPort deckPort, String... columns) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("deckPort", deckPort);
        params.put("columns", columns);
        getSqlSession().update("updateMemberDeckPort", params);
    }

    @Override
    public List<MemberDeckPort> selectMemberDeckPortsByMemberId(long member_id) {
        return getSqlSession().selectList("selectMemberDeckPortsByMemberId", Collections.singletonMap("member_id", member_id));
    }

    @Override
    public MemberDeckPort selectMemberDeckPort(long member_id, Integer deck_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("deck_id", deck_id);
        return getSqlSession().selectOne("selectMemberDeckPortByMemberIdAndDeckId", params);
    }

    @Override
    public MemberDeckPort selectMemberDeckPortContainsMemberShip(long member_id, long member_ship_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("member_ship_id", member_ship_id);
        return getSqlSession().selectOne("selectMemberDeckPortContainsMemberShip", params);
    }

    @Override
    public void updateMemberDeckPortShip(MemberDeckPort targetDeck) {
        List<Long> ship = targetDeck.getShips().stream().map(MemberShip::getMemberShipId).collect(Collectors.toList());

        while (ship.size() < MemberDeckPort.MAX_SHIP_COUNT) {
            ship.add(-1L);
        }

        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("member_id", targetDeck.getMemberId());
        params.put("deck_id", targetDeck.getDeckId());
        params.put("ship", generateJsonArray(ship));
        getSqlSession().update("updateMemberDeckPortShip", params);
    }

    @Override
    public void updateDeckPortMission(MemberDeckPort deckport) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(6);
        params.put("member_id", deckport.getMemberId());
        params.put("deck_id", deckport.getDeckId());
        params.put("mission_status", deckport.getMission()[0]);
        params.put("mission_id", deckport.getMission()[1] == 0 ? null : deckport.getMission()[1]);
        params.put("mission_complete_time", deckport.getMission()[2]);
        params.put("mission_flag", deckport.getMission()[3]);
        getSqlSession().update("updateDeckPortMission", params);
    }

    @Override
    public int insertMemberDeckPorts(List<MemberDeckPort> deckPorts) {
        return getSqlSession().insert("insertMemberDeckPorts", deckPorts);
    }
}
