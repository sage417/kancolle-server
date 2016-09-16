package com.kancolle.server.mapper.map;

import com.kancolle.server.model.kcsapi.start.sub.MapBgmModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Package: com.kancolle.server.mapper.map
 * Author: mac
 * Date: 16/8/10
 */
@Mapper
public interface MapBGMMapper {

    List<MapBgmModel> selectMapBgms();

    void replaceMapBgm(Map<String, Object> stringObjectMap);
}
