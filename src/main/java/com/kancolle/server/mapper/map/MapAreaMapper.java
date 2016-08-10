package com.kancolle.server.mapper.map;

import com.kancolle.server.model.po.map.MapArea;

import java.util.List;

public interface MapAreaMapper {

    List<MapArea> selectMapAreas();

    MapArea selectMapAreaById(int maparea_id);

}
