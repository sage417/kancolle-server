package com.kancolle.server.mapper.ship;

import com.kancolle.server.model.kcsapi.start.sub.ShipGraphModel;

import java.util.List;
import java.util.Map;

/**
 * Created by J.K.SAGE on 2016/8/8 0008.
 */
public interface ShipGraphMapper {

    List<ShipGraphModel> selectShipGraphs();

    void replaceShipGraph(Map<String, Object> shipGraph);
}
