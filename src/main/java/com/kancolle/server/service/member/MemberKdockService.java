/**
 * 
 */
package com.kancolle.server.service.member;

import com.kancolle.server.controller.kcsapi.form.kdock.CreateShipForm;
import com.kancolle.server.model.kcsapi.kcock.GetShipResult;
import com.kancolle.server.model.po.member.MemberKdock;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年7月10日
 *
 */
public interface MemberKdockService {

    List<MemberKdock> getMemberKdocks(long member_id);

    MemberKdock getMemberKdockByCond(long member_id, Integer kdock_id);

    MemberKdock createShip(long member_id, CreateShipForm form);

    void speedUp(long member_id, Integer kdock_id);

    GetShipResult getShip(long member_id, Integer kdock_id);

    void initMemberKdock(long member_id);

}
