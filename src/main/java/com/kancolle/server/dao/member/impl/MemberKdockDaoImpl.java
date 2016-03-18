/**
 * 
 */
package com.kancolle.server.dao.member.impl;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberKdockDao;
import com.kancolle.server.model.po.member.MemberKdock;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author J.K.SAGE
 * @Date 2015年7月10日
 *
 */
@Repository
public class MemberKdockDaoImpl extends BaseDaoImpl<MemberKdock> implements MemberKdockDao {

    @Override
    public List<MemberKdock> selectMemberKdocks(String member_id) {
        return getSqlSession().selectList("selectMemberKdockByCond", Collections.singletonMap("member_id", member_id));
    }

    @Override
    public MemberKdock selectMemberKdockByCond(String member_id, Integer kdock_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("kdock_id", kdock_id);
        return getSqlSession().selectOne("selectMemberKdockByCond", params);
    }

    @Override
    public void insertMemberKdocks(List<MemberKdock> kdocks) {
        getSqlSession().insert("insertMemberKdocks", kdocks);
    }
}
