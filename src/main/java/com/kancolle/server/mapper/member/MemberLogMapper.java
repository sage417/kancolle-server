package com.kancolle.server.mapper.member;

import com.kancolle.server.model.kcsapi.member.MemberLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Package: com.kancolle.server.mapper.member
 * Author: mac
 * Date: 16/9/9
 */
@Mapper
public interface MemberLogMapper {

    int insertMemberLog(MemberLog log);

    List<MemberLog> selectMemberLogs(@Param("member_id") long member_id);

}
