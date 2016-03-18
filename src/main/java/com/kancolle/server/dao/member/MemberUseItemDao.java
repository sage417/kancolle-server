/**
 * 
 */
package com.kancolle.server.dao.member;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.useitem.MemberUseItem;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 *
 */
public interface MemberUseItemDao extends BaseDao<MemberUseItem> {

    List<MemberUseItem> selectMemberUseItems(String member_id);

    int countMemberItem(String member_id, Integer useItem_id);

    void addMemberUseItemCount(String member_id, int useitem_id, int addCount);

    void insertMemberUseItems(long member_id, int[] useItemIds);
}
