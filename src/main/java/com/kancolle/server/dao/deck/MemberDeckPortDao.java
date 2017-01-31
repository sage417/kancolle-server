package com.kancolle.server.dao.deck;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.SlimDeckPort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberDeckPortDao extends BaseDao<MemberDeckPort> {

    List<MemberDeckPort> selectMemberDeckPortsByMemberId(@Param("member_id") long member_id);

    MemberDeckPort selectMemberDeckPort(@Param("member_id") long member_id, @Param("deck_id") Integer deck_id);

    SlimDeckPort selectEagerMemberDeckPortByMemberIdAndDeckId(@Param("member_id") long member_id, @Param("deck_id") Integer deck_id);

    MemberDeckPort selectMemberDeckPortContainsMemberShip(@Param("member_id") long member_id, @Param("member_ship_id") long member_ship_id);

    void updateMemberDeckPortShip(MemberDeckPort targetDeck);

    void updateDeckPortMission(MemberDeckPort deckport);

    int insertMemberDeckPorts(List<MemberDeckPort> deckPorts);
}
