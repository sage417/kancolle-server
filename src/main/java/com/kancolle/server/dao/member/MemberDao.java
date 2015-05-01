package com.kancolle.server.dao.member;

import com.kancolle.server.dao.base.BaseDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;

public interface MemberDao<T> extends BaseDao<T>{

	String getMemberByApiToken(String api_token);

	MemberBasic getBasic(String member_id);

}
