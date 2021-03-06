package com.kancolle.server.mapper.map;

import com.kancolle.server.model.kcsapi.start.sub.MapInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by J.K.SAGE on 2016/4/3 0003.
 */
@Mapper
public interface MapInfoMapper {

    List<MapInfoModel> selectMapInfos();

    MapInfoModel selectMapInfo(@Param("mapArea_id") int mapArea_id, @Param("no") int no);

    MapInfoModel selectMapInfoById(@Param("id") long id);

     void replaceMapInfo(Map<String, Object> stringObjectMap);
}
