/**
 * 
 */
package com.kancolle.server.dao.member;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.po.resource.Resource;

/**
 * @author J.K.SAGE
 * @Date 2015年6月19日
 *
 */
public interface MemberResourceDao extends BaseDao<Resource> {
    /**
     * @param chargeFuel
     * @param chargeBull
     * @param comsumeSteal
     * @param comsumeBauxite
     */
    void updateMemberResource(long memberId, int chargeFuel, int chargeBull, int comsumeSteal, int comsumeBauxite);

    /**
     * @param memberId
     * @return
     */
    Resource selectMemberResource(long memberId);

}
