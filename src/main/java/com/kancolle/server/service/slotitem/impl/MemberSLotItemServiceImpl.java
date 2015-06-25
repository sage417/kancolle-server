/**
 * 
 */
package com.kancolle.server.service.slotitem.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.slotitem.MemberSlotItemDao;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.service.slotitem.MemberSlotItemService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
@Service
public class MemberSLotItemServiceImpl implements MemberSlotItemService {
    @Autowired
    private MemberSlotItemDao memberSlotItemDao;

    @Override
    public MemberSlotItem getMemberSlotItem(String memberId, Long memberSlotItemId) {
        return memberSlotItemDao.selectMemberSlotItem(memberId, memberSlotItemId);
    }
}
