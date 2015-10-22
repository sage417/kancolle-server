/**
 * 
 */
package com.kancolle.server.dao.furniture;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.model.po.furniture.MemberFurniture;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
public interface MemberFurnitureDao extends BaseDao<MemberFurniture> {

    List<MemberFurniture> getFurniture(String member_id);

    MemberFurniture selectMemberFurnitureById(String member_id, Integer furniture_id);

    Furniture selectFurnitureByTypeAndNo(Integer type, Integer no);

    void insertMemberFurniture(String member_id, Integer furnitureId);

    void changeMemberFurniture(String member_id, List<Integer> furnitureIds);

    int selectCountOfMemberFurniture(String member_id);
}
