package com.kancolle.server.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.alibaba.fastjson.JSON;
import com.kancolle.server.model.kcsapi.member.MemberDeckPort;
import com.kancolle.server.utils.DaoUtils;

public class MemberDeckPortMapper implements  RowMapper<MemberDeckPort>{

    @Override
    public MemberDeckPort mapRow(ResultSet rs, int rn) throws SQLException {
        MemberDeckPort deck_port =DaoUtils.setObject(new MemberDeckPort(), rs);
        deck_port.setApi_mission(JSON.toJSONString(new long[]{rs.getInt("MISSION_STATUS"),rs.getInt("MISSION_ID"),rs.getLong("MISSION_COMPLETE_TIME"),rs.getInt("MISSION_FLAG")}));
        return deck_port;
    }
}
