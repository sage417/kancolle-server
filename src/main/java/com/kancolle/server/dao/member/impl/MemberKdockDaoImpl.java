/**
 * 
 */
package com.kancolle.server.dao.member.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberKdockDao;
import com.kancolle.server.model.po.member.MemberKdock;

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
}
