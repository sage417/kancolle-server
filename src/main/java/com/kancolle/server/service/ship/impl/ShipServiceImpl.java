package com.kancolle.server.service.ship.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.utils.logic.LVUtil;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipDao shipDao;

    @Cacheable(value = "ship", key = "#ship_id")
    @Override
    public Ship getShipById(int ship_id) {
        return shipDao.getShipById(ship_id);
    }

    @Override
    public MemberShip getMemberShip(String member_id, long ship_id) {
        return shipDao.getMemberShip(member_id, ship_id);
    }

    @Override
    public MemberShip addMemberShipExp(MemberShip memberShip, int exp) {
        // 当前等级
        int nowLv = memberShip.getLv();
        // 99级和150级不获得经验
        if (LVUtil.isShipLVOver(nowLv))
            return memberShip;
        // 当前总经验
        long[] exps = memberShip.getExp();
        // 获得经验后总经验（未修正前）
        long afterExp = exps[0] + exp;
        // 获得经验后等级（经过修正）
        int afterLv = shipDao.getShipLVByExp(afterExp);

        if (LVUtil.isShipLVOver(afterLv))
            // 获得经验后总经验（修正后）
            afterExp = this.getSumExpByLevel(afterLv);

        // 下一级所需总经验
        long nextLvExp = afterLv > nowLv ? this.getSumExpByLevel(afterLv + 1) : exps[1];

        int progress = (int) Math.floorDiv(100L * (afterExp - this.getSumExpByLevel(afterLv)), this.getNextExpByLevel(afterLv));

        memberShip.setLv(afterLv);
        memberShip.setExp(new long[] { afterExp, nextLvExp - afterExp, progress });

        shipDao.update(memberShip);

        return memberShip;
    }

    /**
     * 
     * @param nowLevel 当前等级
     * @return
     */
    private long getNextExpByLevel(int nowLevel) {
        return this.getSumExpByLevel(nowLevel + 1) - this.getSumExpByLevel(nowLevel);
    }

    @Override
    @Cacheable(value = "shipExp", key = "#level")
    public long getSumExpByLevel(int level) {
        return shipDao.getNeedExpByLevel(level);
    }
}
