package com.kancolle.server.service.ship;

import com.kancolle.server.model.po.ship.BaseShip;
import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.ship.ShipType;

import java.util.List;

public interface ShipService {

    List<BaseShip> getShips();

    List<ShipType> getShipTypes();

    ShipType getShipType(int typeId);

    int getCountOfShipTypes();

    long getSumExpByLevel(int level);

    long getNextLVExp(int nowLevel);

    int getShipLVByExp(long afterExp);

    boolean canEquip(ShipType shipType, int slotitemId);

    Ship getShipById(int ship_id);
}
