/**
 * 
 */
package com.kancolle.server.mapper.map;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kancolle.server.model.po.map.MemberMapInfo;

/**
 * @author J.K.SAGE
 * @Date 2015年8月17日
 *
 */
public interface MemberMapInfoMapper {

    List<MemberMapInfo> selectMemberMapInfosByMemberId(@Param("member_id") String member_id);

    MemberMapInfo selectMemberMapInfo(@Param("member_id") String member_id, @Param("mapInfo_id") int mapInfo_id);

}
