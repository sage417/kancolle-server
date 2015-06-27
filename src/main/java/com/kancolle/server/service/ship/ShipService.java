package com.kancolle.server.service.ship;

import java.util.List;

import com.kancolle.server.model.po.ship.ShipType;

public interface ShipService {

    List<ShipType> getShipTypes();

    ShipType getShipType(int typeId);

    int getCountOfShipTypes();

    long getSumExpByLevel(int level);

    long getNextLVExp(int nowLevel);

    int getShipLVByExp(long afterExp);

    boolean canEquip(int shipTypeId, int slotitemId);

}
