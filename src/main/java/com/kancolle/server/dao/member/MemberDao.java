package com.kancolle.server.dao.member;

import com.kancolle.server.dao.base.BaseDao;

public interface MemberDao<T> extends BaseDao<T>{

	String getMemberByApiToken(String api_token);

}
