package com.kancolle.server.mapper.ship;

import com.kancolle.server.model.kcsapi.start.sub.ShipUpgradeModel;

import java.util.List;

/**
 * Created by J.K.SAGE on 2016/8/8 0008.
 */
public interface ShipUpgradeMapper {

    List<ShipUpgradeModel> selectShipUpgrades();

}
