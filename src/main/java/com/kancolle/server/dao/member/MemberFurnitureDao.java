/**
 * 
 */
package com.kancolle.server.dao.member;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.furniture.FurnitureType;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
public interface MemberFurnitureDao extends BaseDao<Object> {

    void changeFurniture(String member_id, List<Integer> furnitureIds);

    /**
     * @param member_id
     * @param furnitureIds
     * @return
     */
    void checkFurniture(String member_id, List<Integer> furnitureIds);

    /**
     * @param member_id
     * @param furniture_id
     * @param type
     */
    void checkFurnitureType(String member_id, Integer furniture_id, FurnitureType type);
}
