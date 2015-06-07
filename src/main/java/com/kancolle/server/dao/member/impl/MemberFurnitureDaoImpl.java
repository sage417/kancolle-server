/**
 * 
 */
package com.kancolle.server.dao.member.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberFurnitureDao;
import com.kancolle.server.model.po.furniture.FurnitureType;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
@Repository
public class MemberFurnitureDaoImpl extends BaseDaoImpl<Object> implements MemberFurnitureDao {
    @Override
    public void changeFurniture(String member_id, List<Integer> furnitureIds) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("member_id", member_id);
        params.put("furniture", JSON.toJSONString(furnitureIds));
        getTemplate().update("UPDATE t_member SET furniture = :furniture where member_id = :member_id", params);
    }

    @Override
    public void checkFurniture(String member_id, List<Integer> furnitureIds) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("member_id", member_id);
        params.put("furnitureIds", furnitureIds);
        int existsCount = getSqlSession().selectOne("countFurnituresByCond", params);
        if (!(existsCount == furnitureIds.size()))
            throw new IllegalArgumentException("不拥有该家具");
    }

    @Override
    public void checkFurnitureType(String member_id, Integer furniture_id, FurnitureType type) {
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("member_id", member_id);
        params.put("furniture_id", furniture_id);
        params.put("furniture_type", type.getTypeId());
        if (getSqlSession().selectOne("selectMemberFurnitureByCond", params) == null)
            throw new IllegalArgumentException("家具" + furniture_id + "类型错误，期望为" + type.getTypeId());
    }
}
