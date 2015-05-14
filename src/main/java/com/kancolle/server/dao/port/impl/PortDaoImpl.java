package com.kancolle.server.dao.port.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
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
import com.kancolle.server.utils.DaoUtils;

@Repository
public class PortDaoImpl extends BaseDaoImpl<MemberPort> implements PortDao {
    @Autowired
    private MemberDao<?> memberDao;

    @Override
    public MemberBasic getBasic(String member_id) {
        return memberDao.getBasic(member_id);
    }

    @Override
    public List<MemberDeckPort> getDeckPort(String member_id) {
        return getTemplate().query("SELECT * FROM t_member_deckport WHERE member_id = :member_id", getMemParamMap(member_id), (rs, ns) -> {
            MemberDeckPort deck_port = DaoUtils.setObject(new MemberDeckPort(), rs);
            deck_port.setApi_mission(JSON.toJSONString(new long[] { rs.getInt("MISSION_STATUS"), rs.getInt("MISSION_ID"), rs.getLong("MISSION_COMPLETE_TIME"), rs.getInt("MISSION_FLAG") }));
            return deck_port;
        });
    }

    @Override
    public List<MemberLog> getLog(String member_id) {
        return queryForModels(MemberLog.class, "SELECT * FROM t_member_log WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberMeterial> getMaterial(String member_id) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        MemberMeterialDto meterialDto = queryForSingleModel(MemberMeterialDto.class, "SELECT * FROM t_member_material WHERE member_id = :member_id", getMemParamMap(member_id));

        long member = Long.valueOf(member_id);

        return meterialDto.toModel().stream().map(meterial -> {
            meterial.setApi_member_id(member);
            return meterial;
        }).collect(Collectors.toList());
    }

    private Map<String, Object> getMemParamMap(String value) {
        return Collections.singletonMap("member_id", value);
    }

    @Override
    public List<MemberNDock> getNdock(String member_id) {
        return queryForModels(MemberNDock.class, "SELECT * FROM t_member_ndock WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberShip> getShip(String member_id) {
        return queryForModels(MemberShip.class, "SELECT * FROM t_member_ship WHERE member_id = :member_id", getMemParamMap(member_id));
    }
}
