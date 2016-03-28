package com.kancolle.server.service.map.impl;

import com.kancolle.server.dao.map.MapAreaDao;
import com.kancolle.server.model.kcsapi.start.sub.MapAreaModel;
import com.kancolle.server.service.map.MapAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MapAreaServiceImpl implements MapAreaService {

    @Autowired
    private MapAreaDao mapAreaDao;

    @Override
    @Cacheable(value = "map", key = "#maparea_id", cacheManager = "cacheManager")
    public MapAreaModel getMapArea(int maparea_id) {
        return mapAreaDao.getMapArea(maparea_id);
    }
}
