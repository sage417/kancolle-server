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

    @Override
    public void update(MemberDeckPort deckport) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<MemberDeckPort> selectMemberDeckPorts(String member_id) {
        return getSqlSession().selectList("selectMemberDecksPortByCond", Collections.singletonMap("member_id", member_id));
    }

    @Override
    public MemberDeckPort selectMemberDeckPort(String member_id, Integer deck_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("deck_id", deck_id);
        return getSqlSession().selectOne("selectMemberDecksPortByCond", params);
    }

    @Override
    public MemberDeckPort selectMemberDeckPortContainsMemberShip(String member_id, Long member_ship_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("member_ship_id", member_ship_id);
        return getSqlSession().selectOne("selectMemberDeckPortContainsMemberShip", params);
    }

    @Override
    public void updateMemberDeckPortShip(MemberDeckPort targetDeck) {
        List<Long> ship = targetDeck.getShips().stream().map(MemberShip::getMemberShipId).collect(Collectors.toList());

        while (ship.size() < MemberDeckPort.SHIP_COUT_MAX) {
            ship.add(-1L);
        }

        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("member_id", targetDeck.getMemberId());
        params.put("deck_id", targetDeck.getDeckId());
        params.put("ship", generateJsonArray(ship));
        getSqlSession().update("updateMemberDeckPortShip", params);
    }

    @Override
    public void insertDeckPortShip(MemberDeckPort targetDeck, MemberShip memberShip) {
        updateMemberDeckPortShip(targetDeck);
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("member_id", targetDeck.getMemberId());
        params.put("deck_id", targetDeck.getDeckId());
        params.put("member_ship_id", memberShip.getMemberShipId());
        getSqlSession().insert("insertDeckPortShip", params);
    }

    @Override
    public void deleteDeckPortShip(MemberDeckPort targetDeck, List<MemberShip> removeShips) {
        updateMemberDeckPortShip(targetDeck);
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("member_id", targetDeck.getMemberId());
        params.put("deck_id", targetDeck.getDeckId());
        params.put("removeShips", removeShips);
        getSqlSession().delete("deleteDeckPortShip", params);
    }

    @Override
    public void updateDeckPortShip(MemberDeckPort targetDeck, MemberShip repalcedMemberShip, MemberShip memberShip) {
        updateMemberDeckPortShip(targetDeck);
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(4);
        params.put("member_id", targetDeck.getMemberId());
        params.put("deck_id", targetDeck.getDeckId());
        params.put("new_member_ship_id", memberShip.getMemberShipId());
        params.put("member_ship_id", repalcedMemberShip.getMemberShipId());
        getSqlSession().update("updateDeckPortShip", params);
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
    public void updateDeckPortState(String member_id, Integer deckport_id, boolean lock) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(6);
        params.put("member_id", member_id);
        params.put("deck_id", deckport_id);
        params.put("lock", lock);
        getSqlSession().update("updateDeckPortState", params);
    }

    @Override
    public void insertMemberDeckPorts(List<MemberDeckPort> deckPorts) {
        getSqlSession().insert("insertMemberDeckPorts", deckPorts);
    }
}
