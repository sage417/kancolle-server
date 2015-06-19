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
    public void updateMemberResource(long memberId, int chargeFuel, int chargeBull, int comsumeSteal, int comsumeBauxite) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(5);
        params.put("member_id", memberId);
        params.put("fuel", chargeFuel);
        params.put("bull", chargeBull);
        params.put("steal", comsumeSteal);
        params.put("bauxite", comsumeBauxite);
        getSqlSession().update("updateMemberResource", params);
    }

    @Override
    public Resource selectMemberResource(long memberId) {
        return getSqlSession().selectOne("selectMemberResource", memberId);
    }
}
