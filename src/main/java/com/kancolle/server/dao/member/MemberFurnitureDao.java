/**
 * 
 */
package com.kancolle.server.dao.member;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.furniture.Furniture;
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
    void checkFurnituresExist(String member_id, List<Integer> furnitureIds);

    /**
     * @param member_id
     * @param furniture_id
     * @param type
     */
    void checkFurnitureType(String member_id, Integer furniture_id, FurnitureType type);

    /**
     * @param member_id
     * @param api_type
     * @param api_no
     */
    void checkFurnitureBeforeBuy(String member_id, int furnitureId);

    /**
     * @param type
     * @param no
     * @return
     */
    Furniture getFurnitureByTypeAndNo(Integer type, Integer no);

    /**
     * @param member_id
     * @param furnitureId
     */
    void addMemberFurniture(String member_id, int furnitureId);

    /**
     * @param member_id
     * @param price
     */
    void costMemberFurnitureCoin(String member_id, int price);
}
