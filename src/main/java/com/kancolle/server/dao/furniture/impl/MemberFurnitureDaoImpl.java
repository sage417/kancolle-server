/**
 * 
 */
package com.kancolle.server.dao.furniture.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.furniture.MemberFurnitureDao;
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.model.po.furniture.MemberFurniture;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
@Repository
public class MemberFurnitureDaoImpl extends BaseDaoImpl<MemberFurniture> implements MemberFurnitureDao {

    private Map<String, Object> getMemberIdFurnitureIdParamMap(String member_id, Integer furniture_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("furniture_id", furniture_id);
        return params;
    }

    @Override
    public List<MemberFurniture> getFurniture(String member_id) {
        return getSqlSession().selectList("selectMemberFurnitures", member_id);
    }

    @Override
    public void changeMemberFurniture(String member_id, List<Integer> furnitureIds) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("furniture", JSON.toJSONString(furnitureIds));
        getTemplate().update("UPDATE t_member SET furniture = :furniture where member_id = :member_id", params);
    }

    @Override
    public MemberFurniture selectMemberFurnitureById(String member_id, Integer furniture_id) {
        Map<String, Object> params = getMemberIdFurnitureIdParamMap(member_id, furniture_id);
        return getSqlSession().selectOne("selectMemberFurnitureByCond", params);
    }

    @Override
    public Furniture selectFurnitureByTypeAndNo(Integer type, Integer no) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("furniture_type", type);
        params.put("furniture_no", no);
        return getSqlSession().selectOne("selectFurnitureByCond", params);
    }

    @Override
    public void insertMemberFurniture(String member_id, Integer furniture_id) {
        Map<String, Object> params = getMemberIdFurnitureIdParamMap(member_id, furniture_id);
        getSqlSession().insert("insertMemberFurniture", params);
    }

    @Override
    public int selectCountOfMemberFurniture(String member_id) {
        return getSqlSession().selectOne("selectCountOfMemberFurniture", member_id);
    }
}
