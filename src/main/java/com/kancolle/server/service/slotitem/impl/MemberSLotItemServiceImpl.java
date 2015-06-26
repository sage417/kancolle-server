/**
 * 
 */
package com.kancolle.server.service.slotitem.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.slotitem.MemberSlotItemDao;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemLockResult;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.service.slotitem.MemberSlotItemService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月25日
 *
 */
@Service
public class MemberSLotItemServiceImpl implements MemberSlotItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberSLotItemServiceImpl.class);

    @Autowired
    private MemberSlotItemDao memberSlotItemDao;

    @Override
    public MemberSlotItem getMemberSlotItem(String memberId, Long memberSlotItemId) {
        return memberSlotItemDao.selectMemberSlotItem(memberId, memberSlotItemId);
    }

    @Override
    public MemberSlotItemLockResult lock(String member_id, Long slotitem_id) {
        MemberSlotItem memberSlotItem = getMemberSlotItem(member_id, slotitem_id);
        if (memberSlotItem == null) {
            LOGGER.warn("用户ID{}请求不存在的装备ID{}", member_id, slotitem_id);
            throw new IllegalArgumentException();
        }

        Boolean lock = Boolean.valueOf(!memberSlotItem.getLocked());
        memberSlotItemDao.updateMemberSlotItemLockStatue(member_id, slotitem_id, lock);
        return new MemberSlotItemLockResult(lock.booleanValue());
    }
}
