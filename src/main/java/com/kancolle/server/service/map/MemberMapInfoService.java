/**
 * 
 */
package com.kancolle.server.service.map;

import java.util.List;

import com.kancolle.server.model.po.map.MemberMapInfo;

/**
 * @author J.K.SAGE
 * @Date 2015年8月17日
 *
 */
public interface MemberMapInfoService {

    List<MemberMapInfo> getMemberMapInfos(String member_id);

}
