package com.kancolle.server.mapper.ship;

import com.kancolle.server.model.po.ship.BaseShip;
import com.kancolle.server.model.po.ship.Ship;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShipMapper {

    List<Ship> selectShipsByCond();

    List<BaseShip> selectEmShip();

    int selectCountOfShipTypes();

    long selectShipNeedExpByLevel(@Param("level") int nowLv);

    int selectShipLVByExp(@Param("exp") long nowExp);

    Ship selectShipsByCond(@Param("ship_id") int ship_id);

    void replaceShip(Map<String, Object> model);

    void insertBaseShip(Map<String, Object> stringObjectMap);
}
