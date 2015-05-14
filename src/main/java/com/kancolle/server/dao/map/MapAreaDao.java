package com.kancolle.server.dao.map;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.start.sub.MapAreaModel;

public interface MapAreaDao extends BaseDao<MapAreaModel> {

    MapAreaModel getMapArea(int maparea_id);

}
