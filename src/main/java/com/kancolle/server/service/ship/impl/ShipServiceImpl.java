package com.kancolle.server.service.ship.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kancolle.server.dao.ship.ShipDao;
import com.kancolle.server.model.po.ship.BaseShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.ship.ShipType;
import com.kancolle.server.service.ship.ShipService;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipDao shipDao;

    @Override
    public List<BaseShip> getShips() {
        List<BaseShip> ships = Lists.newArrayListWithCapacity(600);
        ships.addAll(shipDao.selectShips());
        ships.addAll(shipDao.selectEmShip());
        return ships;
    }

    @Override
    public List<ShipType> getShipTypes() {
        return shipDao.selectShipTypes();
    }

    @Override
    public int getCountOfShipTypes() {
        return shipDao.selectCountOfShipTypes();
    }

    @Override
    public int getShipLVByExp(long afterExp) {
        return shipDao.getShipLVByExp(afterExp);
    }

    /**
     * 获取舰娘升级到下一级所需经验（差分经验）
     * 
     * @param nowLevel
     * @return
     */
    @Override
    public long getNextLVExp(int nowLevel) {
        return getTargetLVExp(nowLevel, nowLevel + 1);
    }

    private long getTargetLVExp(int startLevel, int targetLevel) {
        return getSumExpByLevel(targetLevel) - getSumExpByLevel(startLevel);
    }

    /**
     * 获取舰娘所需要到此等级的总经验
     */
    @Override
    public long getSumExpByLevel(int level) {
        return shipDao.getNeedExpByLevel(level);
    }

    @Override
    public ShipType getShipType(int typeId) {
        return shipDao.selectShipTypeByCond(typeId);
    }

    @Override
    public boolean canEquip(ShipType shipType, int slotitemId) {
        return shipType.getEquipTypes().getIntValue(Integer.toString(slotitemId)) == 1;
    }

    @Override
    public Ship getShipByCond(int ship_id) {
        return shipDao.selectShipByCond(ship_id);
    }
}
