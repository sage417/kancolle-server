/**
 * 
 */
package com.kancolle.server.mapper.map;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kancolle.server.model.po.map.MemberMapCell;

/**
 * @author J.K.SAGE
 * @Date 2015年8月19日
 *
 */
public interface MemberMapCellMapper {

    List<MemberMapCell> selectMemberMapCellInfos(@Param("member_id") String member_id, @Param("maparea_id") int mapareaId, @Param("map_no") int mapNo);

}
