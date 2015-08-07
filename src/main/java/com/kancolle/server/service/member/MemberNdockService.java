/**
 * 
 */
package com.kancolle.server.service.member;

import java.util.List;

import com.kancolle.server.controller.kcsapi.form.ndock.NdockStartForm;
import com.kancolle.server.model.po.member.MemberNdock;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
public interface MemberNdockService {

    List<MemberNdock> getMemberNdocks(String member_id);

    MemberNdock getMemberNdockByCond(String member_id, int ndockId);

    void start(String member_id, NdockStartForm form);

    void speedChange(String member_id, int api_ndock_id);
}
