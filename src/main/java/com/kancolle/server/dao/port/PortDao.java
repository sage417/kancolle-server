package com.kancolle.server.dao.port;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.member.MemberLog;
import com.kancolle.server.model.kcsapi.member.MemberMeterialDto;
import com.kancolle.server.model.kcsapi.member.MemberPort;

import java.util.List;

public interface PortDao extends BaseDao<MemberPort> {

    List<MemberLog> getLog(String member_id);

    List<MemberMeterialDto> getMaterial(String member_id);
}
