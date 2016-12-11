/**
 * 
 */
package com.kancolle.server.dao.member;

import com.google.common.collect.Maps;
import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.model.po.resource.Resource;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author J.K.SAGE
 * @Date 2015年6月19日
 *
 */
@Repository
public class MemberResourceDao extends BaseDaoImpl<Resource>   {

    @Override
    public void update(Resource resource) {
        throw new UnsupportedOperationException();
    }

    public Resource selectMemberResource(long member_id) {
        return getSqlSession().selectOne("selectMemberResource", member_id);
    }

    public void insertMemberResource(Resource resource) {
        getSqlSession().insert("insertMemberResource", resource);
    }

    public void updateMemberResource(long member_id, int chargeFuel, int chargeBull, int comsumeSteel, int comsumeBauxite, int fastRecovery, int fastBuild, int devItem, int ehItem) {
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
