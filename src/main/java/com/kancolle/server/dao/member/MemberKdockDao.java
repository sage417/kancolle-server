/**
 * 
 */
package com.kancolle.server.dao.member;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.member.MemberKdock;

/**
 * @author J.K.SAGE
 * @Date 2015年7月10日
 *
 */
public interface MemberKdockDao extends BaseDao<MemberKdock> {

    List<MemberKdock> selectMemberKdocks(String member_id);

}
