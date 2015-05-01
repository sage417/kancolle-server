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

    List<MemberMeterial> getApiMaterial(String member_id);

    List<MemberDeckPort> getApiDeckPort(String member_id);

    List<MemberNDock> getApiNdock(String member_id);

    List<MemberShip> getApiShip(String member_id);

    MemberBasic getApiBasic(String member_id);

    List<MemberLog> getApiLog(String member_id);

    int getApi_p_bgm_id(String member_id);

    int getApiParallelQuestCount(String member_id);

}
