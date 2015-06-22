package com.kancolle.server.dao.port;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberDeckPort;
import com.kancolle.server.model.kcsapi.member.MemberLog;
import com.kancolle.server.model.kcsapi.member.MemberMeterial;
import com.kancolle.server.model.kcsapi.member.MemberNdock;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import com.kancolle.server.model.kcsapi.member.MemberShip;

public interface PortDao extends BaseDao<MemberPort> {

    MemberBasic getBasic(String member_id);

    List<MemberDeckPort> getDeckPort(String member_id);

    List<MemberLog> getLog(String member_id);

    List<MemberMeterial> getMaterial(String member_id) throws Exception;

    List<MemberNdock> getNdock(String member_id);

    List<MemberShip> getShip(String member_id);
}
