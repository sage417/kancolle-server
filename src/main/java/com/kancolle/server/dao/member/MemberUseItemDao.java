/**
 * 
 */
package com.kancolle.server.dao.member;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.useitem.MemberUseItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 *
 */
public interface MemberUseItemDao extends BaseDao<Object> {

    List<MemberUseItem> selectMemberUseItems(String member_id);

    int countMemberItem(String member_id, Integer useItem_id);

    void addMemberUseItemCount(String member_id, int useitem_id, int addCount);
}
