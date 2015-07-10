/**
 * 
 */
package com.kancolle.server.service.member;

import java.util.List;

import com.kancolle.server.model.po.member.MemberKdock;

/**
 * @author J.K.SAGE
 * @Date 2015年7月10日
 *
 */
public interface MemberKdockService {

    List<MemberKdock> getMemberKdock(String member_id);

}
