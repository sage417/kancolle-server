package com.kancolle.server.dao.deck;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberDeckPortDao extends BaseDao<MemberDeckPort> {

    List<MemberDeckPort> selectMemberDeckPortsByMemberId(@Param("member_id") String member_id);

    MemberDeckPort selectMemberDeckPort(@Param("member_id") String member_id, @Param("deck_id") Integer deck_id);

    MemberDeckPort selectMemberDeckPortContainsMemberShip(@Param("member_id") String member_id, @Param("member_ship_id") long member_ship_id);

    void updateMemberDeckPortShip(MemberDeckPort targetDeck);

    void updateDeckPortMission(MemberDeckPort deckport);

    void updateDeckPortState(@Param("member_id") String member_id, @Param("deck_id") Integer deck_id, boolean lock);

    void insertMemberDeckPorts(List<MemberDeckPort> deckPorts);
}
