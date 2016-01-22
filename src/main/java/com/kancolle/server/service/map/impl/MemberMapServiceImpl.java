/**
 *
 */
package com.kancolle.server.service.map.impl;

import com.kancolle.server.controller.kcsapi.form.map.MapCellForm;
import com.kancolle.server.mapper.map.MemberMapCellMapper;
import com.kancolle.server.mapper.map.MemberMapInfoMapper;
import com.kancolle.server.model.po.map.MemberMapCell;
import com.kancolle.server.model.po.map.MemberMapInfo;
import com.kancolle.server.service.map.MemberMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年8月17日
 *
 */
@Service
public class MemberMapServiceImpl implements MemberMapService {

    @Autowired
    private MemberMapInfoMapper memberMapInfoMapper;

    @Autowired
    private MemberMapCellMapper memberMapCellMapper;

    @Override
    public List<MemberMapInfo> getMemberMapInfos(String member_id) {
        return memberMapInfoMapper.selectMemberMapInfosByMemberId(member_id);
    }

    @Override
    public List<MemberMapCell> getMemberCellInfos(String member_id, MapCellForm form) {
        int maparea_id = form.getApi_maparea_id();
        int map_no = form.getApi_mapinfo_no();
        return memberMapCellMapper.selectMemberMapCellInfos(member_id, maparea_id, map_no);
    }

    @Override
    public void updateMemberCellPassFlag(String member_id, int mapcell_id, boolean passFlag) {
        memberMapCellMapper.updateMemberMapCellInfo(member_id, mapcell_id, passFlag);
    }
}
