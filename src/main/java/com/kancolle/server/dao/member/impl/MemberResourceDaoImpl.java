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
    public void update(Resource resource) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Resource selectMemberResource(String member_id) {
        return getSqlSession().selectOne("selectMemberResource", member_id);
    }

    @Override
    public void updateMemberResource(String member_id, int chargeFuel, int chargeBull, int comsumeSteel, int comsumeBauxite, int fastRecovery, int fastBuild, int devItem, int ehItem) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(9);
        params.put("member_id", member_id);
        params.put("fuel", chargeFuel);
        params.put("bull", chargeBull);
        params.put("steel", comsumeSteel);
        params.put("bauxite", comsumeBauxite);
        params.put("fastRecovery", fastRecovery);
        params.put("fastBuild", fastBuild);
        params.put("devItem", devItem);
        params.put("ehItem", ehItem);
        getSqlSession().update("updateMemberResource", params);
    }
}
