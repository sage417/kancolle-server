/**
 * 
 */
package com.kancolle.server.dao.member.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberUseItemDao;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 *
 */
@Repository
public class MemberUseItemDaoImpl extends BaseDaoImpl<Object> implements MemberUseItemDao {

    @Override
    public int countMemberItem(String member_id, Integer useitem_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("useitem_id", useitem_id);
        return getSqlSession().selectOne("selectCountOfMemberItem", params);
    }

    @Override
    public void addMemberUseItemCount(String member_id, int useitem_id, int add_count) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("member_id", member_id);
        params.put("useitem_id", useitem_id);
        params.put("add_count", add_count);
        getSqlSession().update("addMemberUseItemCount", params);
    }
}
