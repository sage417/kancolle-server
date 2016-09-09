package com.kancolle.server.dao.port;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.port.dto.MemberMaterial;
import com.kancolle.server.model.kcsapi.member.MemberMaterialDto;
import com.kancolle.server.model.kcsapi.member.MemberPort;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PortDao extends BaseDaoImpl<MemberPort> {

    public List<MemberMaterialDto> getMaterial(String member_id) {
        MemberMaterial memberMaterial = queryForSingleModel(MemberMaterial.class, "SELECT * FROM t_member_material WHERE member_id = :member_id", Collections.singletonMap("member_id", member_id));

        long member = Long.parseLong(member_id);

        return memberMaterial.toModel().stream().map(material -> {
            material.setApi_member_id(member);
            return material;
        }).collect(Collectors.toList());
    }
}
