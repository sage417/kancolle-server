package com.kancolle.server.dao.ship.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.ship.MemberShipDao;
import com.kancolle.server.model.po.ship.MemberShip;

@Repository
public class MemberShipDaoImpl extends BaseDaoImpl<MemberShip> implements MemberShipDao {

    @Override
    @Deprecated
    public void update(MemberShip memberShip) {
        throw new UnsupportedOperationException();
    }

    @Override
    public MemberShip selectMemberShip(String member_id, long ship_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("ship_id", ship_id);
        return getSqlSession().selectOne("selectMemberShipByCond", params);
    }

    @Override
    public List<MemberShip> selectMemberShips(String member_id) {
        return getSqlSession().selectList("selectMemberShipByCond", Collections.singletonMap("member_id", member_id));
    }

    @Override
    public int selectCountOfMemberShips(String member_id) {
        return getSqlSession().selectOne("selectCountOfMemberShip", member_id);
    }

    @Override
    public void chargeMemberShips(String member_id, List<Long> memberShip_ids, int charge_kind) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("member_id", member_id);
        params.put("memberShip_ids", memberShip_ids);
        params.put("charge_kind", charge_kind);
        getSqlSession().update("chargeMemberShips", params);
    }

    @Override
    public void updateMemberExp(MemberShip memberShip) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(8);
        params.put("member_id", memberShip.getMemberId());
        params.put("memberShp_id", memberShip.getMemberShipId());
        params.put("lv", memberShip.getLv());
        params.put("exp", memberShip.getExp());
        getSqlSession().update("updateMemberExp", params);
    }
}
