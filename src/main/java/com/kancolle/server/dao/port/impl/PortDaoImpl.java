package com.kancolle.server.dao.port.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.dao.port.PortDao;
import com.kancolle.server.dao.port.dto.MemberMeterialDto;
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
    public List<MemberMeterial> getApiMaterial(String member_id) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        MemberMeterialDto meterialDto = queryForSingleModel(MemberMeterialDto.class, "SELECT * FROM t_member_meterial WHERE member_id = :member_id", getMemParamMap(member_id));

        long member = Long.valueOf(member_id);

        return meterialDto.toModel().stream().map(meterial -> {
            meterial.setApi_member_id(member);
            return meterial;
        }).collect(Collectors.toList());
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
        return getTemplate().queryForObject("SELECT P_BGM_ID FROM t_member WHERE member_id = :member_id", getMemParamMap(member_id), int.class);
    }

    @Override
    public int getApiParallelQuestCount(String member_id) {
        return getTemplate().queryForObject("SELECT PARALLEL_QUEST_COUNT FROM t_member WHERE member_id = :member_id", getMemParamMap(member_id), int.class);
    }
}
