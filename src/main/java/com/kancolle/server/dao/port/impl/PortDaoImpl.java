package com.kancolle.server.dao.port.impl;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.port.PortDao;
import com.kancolle.server.dao.port.dto.MemberMaterial;
import com.kancolle.server.model.kcsapi.member.MemberLog;
import com.kancolle.server.model.kcsapi.member.MemberMeterialDto;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PortDaoImpl extends BaseDaoImpl<MemberPort> implements PortDao {

    @Override
    public List<MemberLog> getLog(String member_id) {
        return queryForModels(MemberLog.class, "SELECT * FROM t_member_log WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberMeterialDto> getMaterial(String member_id) {
        MemberMaterial memberMeterial = queryForSingleModel(MemberMaterial.class, "SELECT * FROM t_member_material WHERE member_id = :member_id", getMemParamMap(member_id));

        long member = Long.parseLong(member_id);

        return memberMeterial.toModel().stream().map(meterial -> {
            meterial.setApi_member_id(member);
            return meterial;
        }).collect(Collectors.toList());
    }

    private Map<String, Object> getMemParamMap(String value) {
        return Collections.singletonMap("member_id", value);
    }
}
