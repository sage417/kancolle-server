/**
 * 
 */
package com.kancolle.server.service.member.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.kancolle.server.model.po.resource.Resource.MAX_METERIAL_VALUE;
import static com.kancolle.server.model.po.resource.Resource.MAX_RESOURCE_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kancolle.server.dao.member.MemberResourceDao;
import com.kancolle.server.model.po.resource.Resource;
import com.kancolle.server.service.member.MemberResourceService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月19日
 *
 */
@Service
public class MemberResourceServiceImpl implements MemberResourceService {
    @Autowired
    private MemberResourceDao memberResourceDao;

    @Override
    public Resource getMemberResouce(String member_id) {
        return memberResourceDao.selectMemberResource(member_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.SUPPORTS)
    public void consumeResource(String member_id, int chargeFuel, int chargeBull, int comsumeSteel, int comsumeBauxite, int fastRecovery, int fastBuild, int DevItem, int EhItem) {
        Resource resource = getMemberResouce(member_id);
        if (!resource.hasEnoughFuel(chargeFuel) && !resource.hasEnoughBull(chargeBull) && !resource.hasEnoughSteel(comsumeSteel) && !resource.hasEnoughBauxite(comsumeBauxite) && !resource.hasEnoughFastRecovery(fastRecovery) && !resource.hasEnoughFastBuild(fastBuild)
                && !resource.hasEnoughDevItem(DevItem) && !resource.hasEnoughEhItem(EhItem)) {
            // TODO LOG
            throw new IllegalArgumentException();
        }
        memberResourceDao.updateMemberResource(member_id, -chargeFuel, -chargeBull, -comsumeSteel, -comsumeBauxite, -fastRecovery, -fastBuild, -DevItem, -EhItem);
    }

    @Override
    public void increaseResource(String member_id, int increaseFuel, int increaseBull, int increaseSteel, int increaseBauxite, int increaseFastRecovery, int increaseFastBuild, int increaseDevItem, int increaseEhItem) {
        Resource resource = getMemberResouce(member_id);

        if (resource.getFuel() + increaseFuel > MAX_RESOURCE_VALUE) {
            increaseFuel = MAX_RESOURCE_VALUE - resource.getFuel();
        }

        if (resource.getBull() + increaseBull > MAX_RESOURCE_VALUE) {
            increaseBull = MAX_RESOURCE_VALUE - resource.getBull();
        }

        if (resource.getSteel() + increaseSteel > MAX_RESOURCE_VALUE) {
            increaseSteel = MAX_RESOURCE_VALUE - resource.getSteel();
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

        memberResourceDao.updateMemberResource(member_id, increaseFuel, increaseBull, increaseSteel, increaseBauxite, increaseFastRecovery, increaseFastBuild, increaseDevItem, increaseEhItem);
    }

    @Override
    public void increaseMaterial(String member_id, int[] increaseMaterials) {
        increaseMaterial(member_id, increaseMaterials, new int[] { 0, 0, 0, 0 });
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
}
