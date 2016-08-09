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

    Resource getMemberResource(String member_id);

    void consumeResource(String member_id, int chargeFuel, int chargeBull, int comsumeSteel, int comsumeBauxite, int fastRecovery, int fastBuild, int DevItem, int EhItem);

    void increaseResource(String member_id, int increaseFuel, int increaseBull, int comsumeSteel, int increaseBauxite, int increaseFastRecovery, int increaseFastBuild, int increaseDevItem,
            int increaseEhItem);

    void increaseMaterial(String member_id, int[] increaseMaterials);

    void increaseMaterial(String member_id, int[] material, int[] increaseItems);

    void initMemberResource(long member_id);
}
