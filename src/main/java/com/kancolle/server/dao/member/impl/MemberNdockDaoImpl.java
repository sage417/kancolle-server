/**
 * 
 */
package com.kancolle.server.dao.member.impl;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberNdockDao;
import com.kancolle.server.model.po.member.MemberNdock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
@Repository
public class MemberNdockDaoImpl extends BaseDaoImpl<MemberNdock> implements MemberNdockDao {

    @Override
    public List<MemberNdock> selectMemberNdocks(String member_id) {
        return getSqlSession().selectList("selectMemberNdocks", member_id);
    }

    @Override
    public MemberNdock selectMemberNdock(String member_id, int ndock_id) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(2);
        params.put("member_id", member_id);
        params.put("ndock_id", ndock_id);
        return getSqlSession().selectOne("selectMemberNdockByCond", params);
    }

    @Override
    public void insertMemberNdocks(List<MemberNdock> ndocks) {
        getSqlSession().insert("insertMemberNdocks",ndocks);
    }
}
