/**
 * 
 */
package com.kancolle.server.service.member;

import java.util.List;

import com.kancolle.server.model.kcsapi.member.MemberNdock;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
public interface MemberNdockService {

    List<MemberNdock> getMemberNdock(String member_id);
}
