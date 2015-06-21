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

    void consumeResource(long memberId, int chargeFuel, int chargeBull, int comsumeSteal, int comsumeBauxite, int fastRecovery, int fastBuild, int DevItem, int EhItem);

    void increaseResource(long memberId, int increaseFuel, int increaseBull, int increaseSteal, int increaseBauxite, int increaseFastRecovery, int increaseFastBuild, int increaseDevItem,
            int increaseEhItem);

    /**
     * @param memberId
     * @return
     */
    Resource getMemberResouce(long memberId);
}