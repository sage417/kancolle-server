/**
 *
 */
package com.kancolle.server.service.member.impl;

import com.kancolle.server.dao.member.MemberResourceDao;
import com.kancolle.server.model.po.resource.Resource;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.utils.logic.MemberResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.kancolle.server.model.po.resource.Resource.MAX_METERIAL_VALUE;
import static com.kancolle.server.model.po.resource.Resource.MAX_RESOURCE_VALUE;

/**
 * @author J.K.SAGE
 * @Date 2015年6月19日
 */
@Service
public class MemberResourceServiceImpl implements MemberResourceService {

    @Autowired
    private MemberResourceDao memberResourceDao;

    @Override
    public Resource getMemberResource(String member_id) {
        return memberResourceDao.selectMemberResource(member_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.SUPPORTS)
    public void consumeResource(final String member_id, final int chargeFuel, final int chargeBull, final int consumeSteel, final int consumeBauxite,
                                final int fastRecovery, final int fastBuild, final int devItem, final int ehItem) {

        checkArgument(chargeFuel >= 0);
        checkArgument(chargeBull >= 0);
        checkArgument(consumeSteel >= 0);
        checkArgument(consumeBauxite >= 0);
        checkArgument(fastRecovery >= 0);
        checkArgument(fastBuild >= 0);
        checkArgument(devItem >= 0);
        checkArgument(ehItem >= 0);

        final Resource resource = getMemberResource(member_id);

        checkArgument(MemberResourceUtils.hasEnoughResource(resource, chargeFuel, chargeBull, consumeSteel, consumeBauxite,
                fastRecovery, fastBuild, devItem, ehItem));

        memberResourceDao.updateMemberResource(member_id, -chargeFuel, -chargeBull, -consumeSteel, -consumeBauxite, -fastRecovery, -fastBuild, -devItem, -ehItem);
    }

    @Override
    public void increaseResource(String member_id, int increaseFuel, int increaseBull, int consumeSteel, int increaseBauxite, int increaseFastRecovery, int increaseFastBuild, int increaseDevItem, int increaseEhItem) {
        Resource resource = getMemberResource(member_id);

        if (resource.getFuel() + increaseFuel > MAX_RESOURCE_VALUE) {
            increaseFuel = MAX_RESOURCE_VALUE - resource.getFuel();
        }

        if (resource.getBull() + increaseBull > MAX_RESOURCE_VALUE) {
            increaseBull = MAX_RESOURCE_VALUE - resource.getBull();
        }

        if (resource.getSteel() + consumeSteel > MAX_RESOURCE_VALUE) {
            consumeSteel = MAX_RESOURCE_VALUE - resource.getSteel();
        }

        if (resource.getBauxite() + increaseBauxite > MAX_RESOURCE_VALUE) {
            increaseBauxite = MAX_RESOURCE_VALUE - resource.getBauxite();
        }

        if (resource.getFastRecovery() + increaseFastRecovery > MAX_METERIAL_VALUE) {
            increaseFastRecovery = MAX_METERIAL_VALUE - resource.getFastRecovery();
        }

        if (resource.getFastBuild() + increaseFastBuild > MAX_METERIAL_VALUE) {
            increaseFastBuild = MAX_METERIAL_VALUE - resource.getFastBuild();
        }

        if (resource.getDevItem() + increaseDevItem > MAX_METERIAL_VALUE) {
            increaseDevItem = MAX_METERIAL_VALUE - resource.getDevItem();
        }

        if (resource.getEhItem() + increaseEhItem > MAX_METERIAL_VALUE) {
            increaseEhItem = MAX_METERIAL_VALUE - resource.getEhItem();
        }

        memberResourceDao.updateMemberResource(member_id, increaseFuel, increaseBull, consumeSteel, increaseBauxite, increaseFastRecovery, increaseFastBuild, increaseDevItem, increaseEhItem);
    }

    @Override
    public void increaseMaterial(String member_id, int[] increaseMaterials) {
        increaseMaterial(member_id, increaseMaterials, new int[]{0, 0, 0, 0});
    }

    @Override
    public void increaseMaterial(String member_id, int[] increaseMaterials, int[] increaseItems) {
        for (int value : increaseMaterials) {
            checkArgument(value >= 0);
        }
        for (int value : increaseItems) {
            checkArgument(value >= 0);
        }
        increaseResource(member_id, increaseMaterials[0], increaseMaterials[1], increaseMaterials[2], increaseMaterials[3], increaseItems[0], increaseItems[1], increaseItems[2], increaseItems[3]);
    }

    @Override
    public void initMemberResource(long member_id) {
        Resource resource = new Resource(member_id);
        memberResourceDao.insertMemberResource(resource);
    }
}
