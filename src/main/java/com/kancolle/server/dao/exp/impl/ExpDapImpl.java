package com.kancolle.server.dao.exp.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.exp.ExpDao;
import com.kancolle.server.model.po.MissionExp;

public class ExpDapImpl<T> extends BaseDaoImpl<T> implements ExpDao<T> {

    @Override
    public int getMemberLevel(long exp) {
        return getTemplate().queryForObject("SELECT LEVEL FROM t_exp_member WHERE EXP<:now_exp ORDER BY LEVEL DESC LIMIT 0,1", Collections.singletonMap("now_exp", exp), int.class);
    }

    @Override
    public MissionExp getMissionExp(int mission_id) {
        return queryForSingleModel(MissionExp.class, "SELECT * FROM t_mission_exp WHERE ID = :member_id", Collections.singletonMap("mission_id", mission_id));
    }

    @Override
    public int updateMemberExpAndLevel(String member_id, long api_experience, int api_level) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("experience", api_experience);
        params.put("level", api_level);
        return getTemplate().update("UPDATE t_member SET experience = :experience, level = :level WHERE member_id = :member_id", params);
    }

    @Override
    public long getMemberNextExp(int now_level) {
        long base_exp = this.getMemberExp(now_level);
        long next_exp = this.getMemberExp(++now_level);
        return next_exp - base_exp;
    }

    private long getMemberExp(int level) {
        return getTemplate().queryForObject("SELECT LEVEL FROM t_exp_member WHERE LEVEL = :level", Collections.singletonMap("level", level), long.class);
    }

    private long getShipExp(int level) {
        return getTemplate().queryForObject("SELECT LEVEL FROM t_exp_ship WHERE LEVEL = :level", Collections.singletonMap("level", level), long.class);
    }

    @Override
    public int getShipLevel(long exp) {
        return getTemplate().queryForObject("SELECT LEVEL FROM t_exp_ship WHERE EXP<:now_exp ORDER BY LEVEL DESC LIMIT 0,1", Collections.singletonMap("now_exp", exp), int.class);
    }

    @Override
    public int updateShipExpAndLevel(String member_id, long ship_id, long[] experiences, int level) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("level", level);
        params.put("exp", JSON.toJSONString(experiences));
        params.put("member_id", member_id);
        params.put("ship_id", ship_id);
        return getTemplate().update("UPDATE v_member_ship SET LV = :level, EXP = :exp WHERE member_id = :member_id AND ID = :ship_id", params);
    }

    @Override
    public long getShipNextExp(int now_level) {
        return this.getShipExp(++now_level);
    }
}
