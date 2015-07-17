/**
 * 
 */
package com.kancolle.server.service.member;

import java.util.List;

import com.kancolle.server.controller.kcsapi.form.kdock.CreateShipForm;
import com.kancolle.server.model.po.kdock.CreateShipResult;
import com.kancolle.server.model.po.member.MemberKdock;

/**
 * @author J.K.SAGE
 * @Date 2015年7月10日
 *
 */
public interface MemberKdockService {

    List<MemberKdock> getMemberKdocks(String member_id);

    MemberKdock getMemberKdockByCond(String member_id, Integer kdock_id);

    CreateShipResult createShip(String member_id, CreateShipForm form);

}
