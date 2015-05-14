package com.kancolle.server.dao.port;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberDeckPort;
import com.kancolle.server.model.kcsapi.member.MemberLog;
import com.kancolle.server.model.kcsapi.member.MemberMeterial;
import com.kancolle.server.model.kcsapi.member.MemberNDock;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.kcsapi.member.MemberShip;

public interface PortDao extends BaseDao<MemberPort> {

    List<MemberMeterial> getMaterial(String member_id) throws Exception;

    List<MemberDeckPort> getDeckPort(String member_id);

    List<MemberNDock> getNdock(String member_id);

    List<MemberShip> getShip(String member_id);

    MemberBasic getBasic(String member_id);

    List<MemberLog> getLog(String member_id);
}
