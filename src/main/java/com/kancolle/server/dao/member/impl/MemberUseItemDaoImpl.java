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
    public int countMemberItem(String member_id, Integer useItem_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("useItem_id", "useItem_id");
        return getSqlSession().selectOne("selectCountOfMemberItem", params);
    }
}
