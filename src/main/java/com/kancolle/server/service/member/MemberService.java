package com.kancolle.server.service.member;

import com.kancolle.server.model.kcsapi.member.MemberBasic;

public interface MemberService {

	String getMemberByApiToken(String api_token);

	MemberBasic getBasic(String member_id);

}
