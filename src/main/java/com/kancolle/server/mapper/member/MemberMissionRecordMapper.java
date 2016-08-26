package com.kancolle.server.mapper.member;

import org.apache.ibatis.annotations.Param;

/**
 * Package: com.kancolle.server.mapper.member
 * Author: mac
 * Date: 16/3/18
 */
public interface MemberMissionRecordMapper{

    void insertMemberMissionRecords(@Param("member_id") long member_id);
}
