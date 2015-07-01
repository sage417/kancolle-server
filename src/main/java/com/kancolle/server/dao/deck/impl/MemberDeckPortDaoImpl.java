package com.kancolle.server.dao.deck.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.deck.MemberDeckPortDao;
import com.kancolle.server.model.po.member.MemberDeckPort;

@Repository
public class MemberDeckPortDaoImpl extends BaseDaoImpl<MemberDeckPort> implements MemberDeckPortDao {

    @Override
    public List<MemberDeckPort> selectMemberDeckPorts(String member_id) {
        return getSqlSession().selectList("selectMemberDecksPortByCond", member_id);
    }

    @Override
    public MemberDeckPort selectMemberDeckPort(String member_id, Integer deck_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("deck_id", deck_id);
        return getSqlSession().selectOne("selectMemberDecksPortByCond", params);
    }
}
