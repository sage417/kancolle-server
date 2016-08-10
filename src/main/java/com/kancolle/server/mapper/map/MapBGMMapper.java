package com.kancolle.server.mapper.map;

import com.kancolle.server.model.kcsapi.start.sub.MapBgmModel;

import java.util.List;

/**
 * Package: com.kancolle.server.mapper.map
 * Author: mac
 * Date: 16/8/10
 */
public interface MapBGMMapper {

    List<MapBgmModel> selectMapBgms();
}
