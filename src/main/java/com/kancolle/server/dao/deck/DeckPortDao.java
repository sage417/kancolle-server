package com.kancolle.server.dao.deck;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.member.MemberDeckPort;

public interface DeckPortDao extends BaseDao<MemberDeckPort> {

    MemberDeckPort getMemberDeckPort(String member_id, int deck_id);

}
