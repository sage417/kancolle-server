package com.kancolle.server.dao.map.impl;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.map.MapAreaDao;
import com.kancolle.server.model.kcsapi.start.sub.MapAreaModel;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;

@Repository
public class MapAreaDaoImpl extends BaseDaoImpl<MapAreaModel> implements MapAreaDao {

    @Override
    public MapAreaModel getMapArea(int maparea_id) {
        return queryForSingleModel(MapAreaModel.class, "SELECT * FROM t_map_area WHERE ID = :maparea_id", getMapAreaIdParamMap(maparea_id));
    }

    private Map<String, Object> getMapAreaIdParamMap(int maparea_id) {
        return Collections.singletonMap("maparea_id", maparea_id);
    }
}
