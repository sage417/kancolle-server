/**
 * 
 */
package com.kancolle.server.dao.furniture;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.model.po.furniture.MemberFurniture;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
public interface MemberFurnitureDao extends BaseDao<MemberFurniture> {

    List<MemberFurniture> getFurniture(long member_id);

    MemberFurniture selectMemberFurnitureById(long member_id, Integer furniture_id);

    Furniture selectFurnitureByTypeAndNo(Integer type, Integer no);

    void insertMemberFurniture(long member_id, Integer furnitureId);

    void changeMemberFurniture(long member_id, int[] furnitureIds);

    int selectCountOfMemberFurniture(long member_id);

    void insertFurnituresForNewMember(long member_id);
}
