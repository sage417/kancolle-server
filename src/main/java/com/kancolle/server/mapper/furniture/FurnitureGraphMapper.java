package com.kancolle.server.mapper.furniture;

import com.kancolle.server.model.kcsapi.start.sub.FurnitureGraphModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Package: com.kancolle.server.mapper
 * Author: mac
 * Date: 16/8/10
 */
@Mapper
public interface FurnitureGraphMapper {

    List<FurnitureGraphModel> selectFurnitureGraphs();

    void replaceFurnitureGraph(Map<String, Object> stringObjectMap);
}
