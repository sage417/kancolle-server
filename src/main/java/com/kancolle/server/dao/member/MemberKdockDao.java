/**
 * 
 */
package com.kancolle.server.dao.member;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.member.MemberKdock;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年7月10日
 *
 */
public interface MemberKdockDao extends BaseDao<MemberKdock> {

    List<MemberKdock> selectMemberKdocks(long member_id);

    MemberKdock selectMemberKdockByCond(long member_id, Integer kdock_id);

    void insertMemberKdocks(List<MemberKdock> kdocks);
}
