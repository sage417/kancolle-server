package com.kancolle.server.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.member.MemberDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.service.member.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao<?> memberDao;

	@Override
	public String getMemberByApiToken(String api_token) {
		return memberDao.getMemberByApiToken(api_token);
	}

	@Override
	public MemberBasic getBasic(String member_id) {
		return memberDao.getBasic(member_id);
	}
}
