package com.kancolle.server.mapper.map;

import com.kancolle.server.model.po.map.MapCellModel;
import com.kancolle.server.model.po.map.MapCellNext;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by J.K.SAGE on 2016/1/21 0021.
 */
@Mapper
public interface MapCellMapper {

    List<MapCellModel> selectMapCells();
    
    MapCellModel selectMapCellById(int mapCellId);

    MapCellNext selectMapCellNextById(int mapCellId);

    void replaceMapCell(Map<String, Object> stringObjectMap);

}
