/**
 * 
 */
package com.kancolle.server.service.map;

import java.util.List;

import com.kancolle.server.controller.kcsapi.form.map.MapCellForm;
import com.kancolle.server.model.po.map.MemberMapCell;
import com.kancolle.server.model.po.map.MemberMapInfo;

/**
 * @author J.K.SAGE
 * @Date 2015年8月17日
 *
 */
public interface MemberMapService {

    List<MemberMapInfo> getMemberMapInfos(String member_id);

    List<MemberMapCell> getMemberCellInfos(String member_id, MapCellForm form);
}
