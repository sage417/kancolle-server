package com.kancolle.server.dao.member.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.dao.slotitem.MemberSlotItemDao;
import com.kancolle.server.dao.slotitem.SlotItemDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberMission;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;
import com.kancolle.server.model.kcsapi.member.record.MemberRecord;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;

@Repository
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {

    @Autowired
    private SlotItemDao slotItemDao;

    @Autowired
    private MemberSlotItemDao memberSlotItemDao;

    @Autowired
    private ShipDao shipDao;

    @Override
    public MemberBasic getBasic(String member_id) {
        return queryForSingleModel(MemberBasic.class, "SELECT * FROM t_member WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberFurniture> getFurniture(String member_id) {
        return queryForModels(MemberFurniture.class, "SELECT * FROM v_member_furniture WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberKdock> getKdock(String member_id) {
        return queryForModels(MemberKdock.class, "SELECT * FROM v_member_kdock WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public String getMemberByApiToken(String api_token) {
        return getTemplate().queryForObject("SELECT member_id FROM t_member WHERE api_token = :token", Collections.singletonMap("token", api_token), String.class);
    }

    private Map<String, Object> getMemParamMap(Object memberId) {
        return Collections.singletonMap("member_id", memberId);
    }

    @Override
    public List<MemberMission> getMission(String member_id) {
        return queryForModels(MemberMission.class, "SELECT * FROM v_member_mission WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public MemberRecord selectMemberRecord(String member_id) {
        return getSqlSession().selectOne("selectMemberRecord", member_id);
    }

    @Override
    public List<MemberSlotItem> getSlotItem(String member_id) {
        return queryForModels(MemberSlotItem.class, "SELECT * FROM v_member_slotitem WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberUseItem> getUseItem(String member_id) {
        return queryForModels(MemberUseItem.class, "SELECT * FROM v_member_useitem WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public long getNeedExpByLevel(int nowLv) {
        return getSqlSession().selectOne("selectNeedMemberExpByLevel", nowLv);
    }

    @Override
    public int getMemberLVByExp(long nowExp) {
        return getSqlSession().selectOne("selectMemberLVByExp", nowExp);
    }

    @Override
    public Member getMemberById(String memberId) {
        return getSqlSession().selectOne("selectMemberById", memberId);
    }
}
