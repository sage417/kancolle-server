package com.kancolle.server.service.map.impl;

import com.kancolle.server.dao.map.MapAreaDao;
import com.kancolle.server.mapper.map.MapInfoMapper;
import com.kancolle.server.model.kcsapi.start.sub.MapAreaModel;
import com.kancolle.server.model.kcsapi.start.sub.MapInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MapService {

    @Autowired
    private MapAreaDao mapAreaDao;

    @Autowired
    private MapInfoMapper mapInfoMapper;

    @Cacheable(value = "map", key = "#maparea_id", cacheManager = "cacheManager")
    public MapAreaModel getMapArea(int maparea_id) {
        return mapAreaDao.getMapArea(maparea_id);
    }

    public MapInfoModel getMapInfoById(long id){
        return mapInfoMapper.selectMapInfoById(id);
    }

    public MapInfoModel getMapInfo(int mapArea_id, int no){
        return mapInfoMapper.selectMapInfo(mapArea_id, no);
    }
}
