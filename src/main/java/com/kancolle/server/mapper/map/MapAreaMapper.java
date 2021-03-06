package com.kancolle.server.mapper.map;

import com.kancolle.server.model.po.map.MapArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MapAreaMapper {

    List<MapArea> selectMapAreas();

    MapArea selectMapAreaById(@Param("maparea_id") int maparea_id);

}
