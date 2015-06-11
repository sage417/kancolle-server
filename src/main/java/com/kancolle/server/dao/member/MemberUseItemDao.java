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

    /**
     * @param member_id
     * @param useItem_id
     * @return
     */
    int countMemberItem(String member_id, Integer useItem_id);

}
