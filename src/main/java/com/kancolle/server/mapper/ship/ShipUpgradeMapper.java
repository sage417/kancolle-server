package com.kancolle.server.mapper.ship;

import com.kancolle.server.model.kcsapi.start.sub.ShipUpgradeModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by J.K.SAGE on 2016/8/8 0008.
 */
@Mapper
public interface ShipUpgradeMapper {

    List<ShipUpgradeModel> selectShipUpgrades();

    void replaceShipGrade(Map<String, Object> stringObjectMap);
}
