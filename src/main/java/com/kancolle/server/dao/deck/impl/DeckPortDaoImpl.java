package com.kancolle.server.dao.deck.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.deck.DeckPortDao;
import com.kancolle.server.model.kcsapi.member.MemberDeckPort;

@Repository
public class DeckPortDaoImpl extends BaseDaoImpl<MemberDeckPort> implements DeckPortDao {

    @Override
    public MemberDeckPort getMemberDeckPort(String member_id, int deck_id) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("member_id", member_id);
        params.put("deck_id", deck_id);
        return queryForSingleModel(MemberDeckPort.class, "SELECT * FROM v_member_deckport WHERE member_id = :member_id AND ID = :deck_id", params);
    }
}
