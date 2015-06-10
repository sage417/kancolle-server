/**
 * 
 */
package com.kancolle.server.dao.member;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.furniture.Furniture;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
public interface MemberFurnitureDao extends BaseDao<Object> {


    /**
     * @param member_id
     * @param furniture_id
     * @param type
     */
    Furniture selectMemberFurnitureById(String member_id, Integer furniture_id);

    /**
     * @param type
     * @param no
     * @return
     */
    Furniture selectFurnitureByTypeAndNo(Integer type, Integer no);

    /**
     * @param member_id
     * @param furnitureId
     */
    void insertMemberFurniture(String member_id, Integer furnitureId);

    /**
     * @param member_id
     * @param furnitureIds
     */
    void changeMemberFurniture(String member_id, List<Integer> furnitureIds);

}
