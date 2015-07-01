package com.kancolle.server.dao.deck;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.member.MemberDeckPort;

public interface MemberDeckPortDao extends BaseDao<MemberDeckPort> {

    List<MemberDeckPort> selectMemberDeckPorts(String member_id);

    MemberDeckPort selectMemberDeckPort(String member_id, Integer deck_id);

}
