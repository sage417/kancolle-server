package com.kancolle.server.service.ship;

import java.util.List;

import com.kancolle.server.model.po.ship.Ship;
import com.kancolle.server.model.po.ship.ShipType;

public interface ShipService {

    List<Ship> getShips();

    List<ShipType> getShipTypes();

    ShipType getShipType(int typeId);

    int getCountOfShipTypes();

    long getSumExpByLevel(int level);

    long getNextLVExp(int nowLevel);

    int getShipLVByExp(long afterExp);

    boolean canEquip(ShipType shipType, int slotitemId);

    Ship getShipByCond(int ship_id);
}
