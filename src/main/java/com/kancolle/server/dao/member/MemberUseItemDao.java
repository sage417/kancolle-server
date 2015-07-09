/**
 * 
 */
package com.kancolle.server.dao.member;

import com.kancolle.server.dao.base.BaseDao;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 *
 */
public interface MemberUseItemDao extends BaseDao<Object> {

    int countMemberItem(String member_id, Integer useItem_id);

    void addMemberUseItemCount(String member_id, int useitem_id, int addCount);
}
