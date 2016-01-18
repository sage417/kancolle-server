package com.kancolle.server.service.map.mapcells;

import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;

/**
 * Created by J.K.SAGE on 2016/1/18.
 */
public interface IMapCell {

    AbstractMapCell getNextMapPoint();

    MapNextResult getMapNextResult();
}
