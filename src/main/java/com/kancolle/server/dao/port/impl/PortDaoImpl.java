package com.kancolle.server.dao.port.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.dao.port.PortDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberDeckPort;
import com.kancolle.server.model.kcsapi.member.MemberLog;
import com.kancolle.server.model.kcsapi.member.MemberMeterial;
import com.kancolle.server.model.kcsapi.member.MemberNDock;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.kcsapi.member.MemberShip;

@Repository
public class PortDaoImpl extends BaseDaoImpl<MemberPort> implements PortDao {
    @Autowired
    private MemberDao<?> memberDao;

    private Map<String, Object> getMemParamMap(String value) {
        return Collections.singletonMap("member_id", value);
    }

    @Override
    public List<MemberMeterial> getApiMaterial(String member_id) {
        return queryForModels(MemberMeterial.class, "SELECT * FROM t_member_meterial WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberDeckPort> getApiDeckPort(String member_id) {
        return queryForModels(MemberDeckPort.class, "SELECT * FROM t_member_deck WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberNDock> getApiNdock(String member_id) {
        return queryForModels(MemberNDock.class, "SELECT * FROM t_member_ndock WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberShip> getApiShip(String member_id) {
        return queryForModels(MemberShip.class, "SELECT * FROM t_member_ship WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public MemberBasic getApiBasic(String member_id) {
        return memberDao.getBasic(member_id);
    }

    @Override
    public List<MemberLog> getApiLog(String member_id) {
        return queryForModels(MemberLog.class, "SELECT * FROM t_member_log WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public int getApi_p_bgm_id(String member_id) {
        return getTemplate().queryForObject("SELECT * FROM t_member_bgm WHERE member_id = :member_id", getMemParamMap(member_id), int.class);
    }

    @Override
    public int getApiParallelQuestCount(String member_id) {
        return getTemplate().queryForObject("SELECT * FROM t_member_quest WHERE member_id = :member_id", getMemParamMap(member_id), int.class);
    }
}
