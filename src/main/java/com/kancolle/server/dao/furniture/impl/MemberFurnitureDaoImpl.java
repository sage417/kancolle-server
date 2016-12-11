/**
 *
 */
package com.kancolle.server.dao.furniture.impl;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.furniture.MemberFurnitureDao;
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.model.po.furniture.MemberFurniture;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 */
@Repository
public class MemberFurnitureDaoImpl extends BaseDaoImpl<MemberFurniture> implements MemberFurnitureDao {

    private Map<String, Object> getMemberIdFurnitureIdParamMap(long member_id, Integer furniture_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("furniture_id", furniture_id);
        return params;
    }

    @Override
    public List<MemberFurniture> getFurniture(long member_id) {
        return getSqlSession().selectList("selectMemberFurnitures", member_id);
    }

    @Override
    public void changeMemberFurniture(long member_id, int[] furnitureIds) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("furniture", furnitureIds);
        getSqlSession().update("changeMemberFurniture", params);
    }

    @Override
    public MemberFurniture selectMemberFurnitureById(long member_id, Integer furniture_id) {
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
    public void insertMemberFurniture(long member_id, Integer furniture_id) {
        Map<String, Object> params = getMemberIdFurnitureIdParamMap(member_id, furniture_id);
        getSqlSession().insert("insertMemberFurniture", params);
    }

    @Override
    public int selectCountOfMemberFurniture(long member_id) {
        return getSqlSession().selectOne("selectCountOfMemberFurniture", member_id);
    }

    @Override
    public void insertFurnituresForNewMember(long member_id) {
        getSqlSession().insert("insertFurnituresForNewMember", member_id);
    }
}
