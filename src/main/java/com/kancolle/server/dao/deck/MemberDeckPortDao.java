package com.kancolle.server.dao.deck;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.MemberShip;

import java.util.List;

public interface MemberDeckPortDao extends BaseDao<MemberDeckPort> {

    List<MemberDeckPort> selectMemberDeckPorts(String member_id);

    MemberDeckPort selectMemberDeckPort(String member_id, Integer deck_id);

    MemberDeckPort selectMemberDeckPortContainsMemberShip(String member_id, Long member_ship_id);

    void insertDeckPortShip(MemberDeckPort targetDeck, MemberShip memberShip);

    void deleteDeckPortShip(MemberDeckPort targetDeck, List<MemberShip> removeShips);

    void updateDeckPortShip(MemberDeckPort targetDeck, MemberShip replacedmemberShip, MemberShip memberShip);

    void updateMemberDeckPortShip(MemberDeckPort targetDeck);

    void updateDeckPortMission(MemberDeckPort deckport);

    void updateDeckPortState(String member_id, Integer deckport_id, boolean lock);

    void insertMemberDeckPorts(List<MemberDeckPort> deckPorts);
}
