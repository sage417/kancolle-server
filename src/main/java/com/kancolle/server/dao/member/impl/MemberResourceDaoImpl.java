/**
 * 
 */
package com.kancolle.server.dao.member.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberResourceDao;
import com.kancolle.server.model.po.resource.Resource;

/**
 * @author J.K.SAGE
 * @Date 2015年6月19日
 *
 */
@Repository
public class MemberResourceDaoImpl extends BaseDaoImpl<Resource> implements MemberResourceDao {

    @Override
    public Resource selectMemberResource(long memberId) {
        return getSqlSession().selectOne("selectMemberResource", memberId);
    }

    @Override
    public void updateMemberResource(long memberId, int chargeFuel, int chargeBull, int comsumeSteal, int comsumeBauxite, int fastRecovery, int fastBuild, int devItem, int ehItem) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(9);
        params.put("member_id", memberId);
        params.put("fuel", chargeFuel);
        params.put("bull", chargeBull);
        params.put("steal", comsumeSteal);
        params.put("bauxite", comsumeBauxite);
        params.put("fastRecovery", fastRecovery);
        params.put("fastBuild", fastBuild);
        params.put("devItem", devItem);
        params.put("ehItem", ehItem);
        getSqlSession().update("updateResource", params);
    }
}
