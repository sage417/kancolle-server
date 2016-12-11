/**
 *
 */
package com.kancolle.server.service.map;

import com.kancolle.server.controller.kcsapi.form.map.MapCellForm;
import com.kancolle.server.model.po.map.MemberMapCell;
import com.kancolle.server.model.po.map.MemberMapInfo;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年8月17日
 *
 */
public interface MemberMapService {

    List<MemberMapInfo> getMemberMapInfos(long member_id);

    List<MemberMapCell> getMemberCellInfos(long member_id, MapCellForm form);

    void updateMemberCellPassFlag(long member_id, int mapCell_id, boolean passFlag);

    void initMemberMapCellInfo(long member_id);

    void initMemberMapInfo(long member_id);
}
