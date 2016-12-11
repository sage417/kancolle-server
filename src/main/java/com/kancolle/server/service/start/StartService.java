package com.kancolle.server.service.start;

import com.kancolle.server.dao.start.StartDao;
import com.kancolle.server.model.kcsapi.start.StartResult;
import com.kancolle.server.model.kcsapi.start.requireInfo.RequireInfo;
import com.kancolle.server.service.furniture.MemberFurnitureService;
import com.kancolle.server.service.member.MemberKdockService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.slotitem.MemberSlotItemService;
import com.kancolle.server.service.useitem.MemberUseItemService;
import com.kancolle.server.utils.DaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartService {

    @Autowired
    private StartDao startDao;

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberSlotItemService memberSlotItemService;
    @Autowired
    private MemberKdockService memberKdockService;
    @Autowired
    private MemberUseItemService memberUseItemService;
    @Autowired
    private MemberFurnitureService memberFurnitureService;

    public StartResult getStartModel() throws InstantiationException, IllegalAccessException {
        StartResult startResult = DaoUtils.setBean(startDao, new Class<?>[]{}, new Object[]{});
        return startResult;
    }

    public RequireInfo getRequireInfo(long member_id) {
        RequireInfo requireInfo = new RequireInfo();

        requireInfo.setApi_basic(memberService.getBasic2(member_id));
        requireInfo.setApi_slot_item(memberSlotItemService.getMemberSlotItems(member_id));
        requireInfo.setApi_unsetslot(memberSlotItemService.getUnsetSlotMap(member_id));
        requireInfo.setApi_kdock(memberKdockService.getMemberKdocks(member_id));
        requireInfo.setApi_useitem(memberUseItemService.getMemberUseItems(member_id));
        requireInfo.setApi_furniture(memberFurnitureService.getFurniture(member_id));

        return requireInfo;
    }
}
