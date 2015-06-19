/**
 * 
 */
package com.kancolle.server.service.member;

import com.kancolle.server.model.po.resource.Resource;

/**
 * @author J.K.SAGE
 * @Date 2015年6月19日
 *
 */
public interface MemberResourceService {
    /**
     * @param memberId
     * @param chargeFuel
     * @param chargeBull
     * @param comsumeSteal
     * @param comsumeBauxite
     */
    void consumeResource(long memberId, int chargeFuel, int chargeBull, int comsumeSteal, int comsumeBauxite);

    /**
     * @param memberId
     * @return
     */
    Resource getMemberResouce(long memberId);

}
