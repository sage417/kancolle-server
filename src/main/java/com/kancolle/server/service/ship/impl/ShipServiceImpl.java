package com.kancolle.server.service.ship.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.kancolle.server.mapper.ship.ShipMapper;
import com.kancolle.server.mapper.ship.ShipTypeMapper;
import com.kancolle.server.model.po.ship.BaseShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.ship.ShipType;
import com.kancolle.server.service.ship.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipMapper shipMapper;
    @Autowired
    private ShipTypeMapper shipTypeMapper;

    @Override
    public List<BaseShip> getShips() {
        List<BaseShip> ships = Lists.newArrayListWithCapacity(600);
        ships.addAll(shipMapper.selectShipsByCond());
        ships.addAll(shipMapper.selectEmShip());
        return ImmutableList.copyOf(ships);
    }

    @Override
    public List<ShipType> getShipTypes() {
        return shipTypeMapper.selectShipTypes();
    }

    @Override
    public int getCountOfShipTypes() {
        return shipMapper.selectCountOfShipTypes();
    }

    @Override
    public int getShipLVByExp(long afterExp) {
        return shipMapper.selectShipLVByExp(afterExp);
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
        return shipMapper.selectShipNeedExpByLevel(level);
    }

    @Override
    public ShipType getShipType(int typeId) {
        return shipTypeMapper.selectShipTypeByCond(typeId);
    }

    @Override
    public boolean canEquip(ShipType shipType, int slotItem_id) {
        return shipType.getEquipTypes().get(Integer.toString(slotItem_id)) == 1;
    }

    @Override
    public Ship getShipById(int ship_id) {
        return shipMapper.selectShipsByCond(ship_id);
    }
}
