package com.kancolle.server.mapper.map;

import com.kancolle.server.model.po.map.MapCell;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by J.K.SAGE on 2016/1/21 0021.
 */
@Mapper
public interface MapCellMapper {

    MapCell selectMapCellById(int mapCellId);

    void replaceMapCell(Map<String, Object> stringObjectMap);
}
