/**
 * 
 */
package com.kancolle.server.dao.member;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.member.MemberNdock;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
public interface MemberNdockDao extends BaseDao<MemberNdock> {

    /**
     * @param member_id
     * @return
     */
    List<MemberNdock> selectMemberNdock(String member_id);

}
