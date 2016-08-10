package com.kancolle.server.service.map.impl;

import com.kancolle.server.mapper.map.MapAreaMapper;
import com.kancolle.server.mapper.map.MapInfoMapper;
import com.kancolle.server.model.kcsapi.start.sub.MapInfoModel;
import com.kancolle.server.model.po.map.MapArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MapService {

    @Autowired
    private MapAreaMapper mapAreaMapper;

    @Autowired
    private MapInfoMapper mapInfoMapper;

    @Cacheable(value = "map", key = "#maparea_id", cacheManager = "cacheManager")
    public MapArea getMapArea(int maparea_id) {
        return mapAreaMapper.selectMapAreaById(maparea_id);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MapInfoModel getMapInfoById(long id) {
        return mapInfoMapper.selectMapInfoById(id);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MapInfoModel getMapInfo(int mapArea_id, int no) {
        return mapInfoMapper.selectMapInfo(mapArea_id, no);
    }
}
