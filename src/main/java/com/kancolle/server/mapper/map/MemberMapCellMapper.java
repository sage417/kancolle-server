/**
 * 
 */
package com.kancolle.server.mapper.map;

import com.kancolle.server.model.po.map.MemberMapCell;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年8月19日
 *
 */
@Mapper
public interface MemberMapCellMapper {

    List<MemberMapCell> selectMemberMapCellInfos(@Param("member_id") long member_id, @Param("maparea_id") int mapareaId, @Param("map_no") int mapNo);

    void updateMemberMapCellInfo(@Param("member_id") long member_id,@Param("mapcell_id") int mapcell_id, @Param("passed") boolean passed);

    void insertMemberMapCellInfos(@Param("member_id")long member_id);
}
