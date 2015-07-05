/**
 * 
 */
package com.kancolle.server.dao.member;

import java.util.List;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.member.MemberNdock;

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
    List<MemberNdock> selectMemberNdocks(String member_id);

    /**
     * @param member_id
     * @param ndockId
     * @return
     */
    MemberNdock selectMemberNdock(String member_id, int ndockId);

}
