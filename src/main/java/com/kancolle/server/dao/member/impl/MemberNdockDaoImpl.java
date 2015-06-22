/**
 * 
 */
package com.kancolle.server.dao.member.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberNdockDao;
import com.kancolle.server.model.kcsapi.member.MemberNdock;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
@Repository
public class MemberNdockDaoImpl extends BaseDaoImpl<MemberNdock> implements MemberNdockDao {

    @Override
    public List<MemberNdock> selectMemberNdock(String member_id) {
        return getSqlSession().selectList("selectMemberNdockByCond", member_id);
    }
}
