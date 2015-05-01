package com.kancolle.server.dao.member.impl;

import org.springframework.stereotype.Repository;

import com.kancolle.server.dao.base.impl.BaseDaoImpl;
import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;

@Repository
public class MemberDaoImpl<T> extends BaseDaoImpl<T> implements MemberDao<T> {

	@Override
	public String getMemberByApiToken(String api_token) {
		return getTemplate().queryForObject("SELECT member_id FROM t_member WHERE api_token = ?", String.class, api_token);
	}

	@Override
	public MemberBasic getBasic(String member_id) {
		return queryForModel(MemberBasic.class, "SELECT * FROM t_member WHERE member_id = ?", member_id);
	}
}
