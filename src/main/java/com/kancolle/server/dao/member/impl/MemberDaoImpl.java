package com.kancolle.server.dao.member.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberFurniture;
import com.kancolle.server.model.kcsapi.member.MemberKdock;
import com.kancolle.server.model.kcsapi.member.MemberSlotItem;
import com.kancolle.server.model.kcsapi.member.MemberUseItem;

@Repository
public class MemberDaoImpl<T> extends BaseDaoImpl<T> implements MemberDao<T> {

    protected Map<String, Object> getMemParamMap(String value) {
        return Collections.singletonMap("member_id", value);
    }

    @Override
    public String getMemberByApiToken(String api_token) {
        return getTemplate().queryForObject("SELECT member_id FROM t_member WHERE api_token = :token", Collections.singletonMap("token", api_token), String.class);
    }

    @Override
    public MemberBasic getBasic(String member_id) {
        return queryForSingleModel(MemberBasic.class, "SELECT * FROM t_member WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberFurniture> getFurniture(String member_id) {
        String furniture_ids = getTemplate().queryForObject("SELECT furniture FROM t_member WHERE member_id = :member_id", getMemParamMap(member_id), String.class);
        furniture_ids = furniture_ids.substring(1, furniture_ids.length() - 1);

        List<MemberFurniture> furnitures = queryForModels(MemberFurniture.class, "SELECT * FROM t_furniture WHERE ID IN (:ids)",
                Collections.singletonMap("ids", Arrays.asList(furniture_ids.split(","))));

        return furnitures.parallelStream().map(furniture -> {
            furniture.setApi_member_id(member_id);
            return furniture;
        }).collect(Collectors.toList());
    }

    @Override
    public List<MemberSlotItem> getSlotItem(String member_id) {
        return queryForModels(MemberSlotItem.class, "SELECT * FROM v_member_slotitem WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberUseItem> getUseItem(String member_id) {
        return queryForModels(MemberUseItem.class, "SELECT * FROM v_member_useitem WHERE member_id = :member_id", getMemParamMap(member_id));
    }

    @Override
    public List<MemberKdock> getKdock(String member_id) {
        return queryForModels(MemberKdock.class, "SELECT * FROM t_member_kdock WHERE member_id = :member_id", getMemParamMap(member_id));
    }
}
