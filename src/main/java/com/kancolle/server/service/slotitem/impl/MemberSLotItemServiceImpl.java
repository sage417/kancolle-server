/**
 * 
 */
package com.kancolle.server.service.slotitem.impl;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.slotitem.MemberSlotItemDao;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemDestoryResult;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemLockResult;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.slotitem.MemberSlotItemService;
import com.kancolle.server.service.slotitem.SlotItemService;

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

    @Autowired
    private SlotItemService slotItemService;

    @Autowired
    private MemberResourceService memberResourceService;

    @Override
    public MemberSlotItem getMemberSlotItem(String memberId, Long memberSlotItemId) {
        return memberSlotItemDao.selectMemberSlotItem(memberId, memberSlotItemId);
    }

    @Override
    public List<MemberSlotItem> getSlotItem(String member_id) {
        return memberSlotItemDao.selectMemberSlotItems(member_id);
    }

    @Override
    public Map<String, Object> getUnsetSlot(String member_id) {
        List<MemberSlotItem> unsetSlots = memberSlotItemDao.selectMemberUnSlots(member_id);

        int slotTypeCount = slotItemService.getCountOfSlotItemTypes();

        Map<String, Object> unslotMap = new LinkedHashMap<String, Object>(slotTypeCount);

        Stream.iterate(1, n -> ++n).limit(slotTypeCount).forEach(i -> {
            List<Long> ids = unsetSlots.stream().filter(slotitem -> slotitem.getSlotItem().getType()[2] == i).map(MemberSlotItem::getMemberSlotItemId).collect(Collectors.toList());
            unslotMap.put(MessageFormat.format("api_slottype{0}", i), ids.isEmpty() ? -1 : ids);
        });
        return unslotMap;
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

    @Override
    public void destorySlotitems(String member_id, List<MemberSlotItem> removeSlotitems) {
        for (MemberSlotItem memberSlotItem : removeSlotitems) {
            if (memberSlotItem == null || memberSlotItem.getLocked())
                throw new IllegalStateException();
        }
        List<Long> slotitem_ids = removeSlotitems.stream().map(MemberSlotItem::getMemberSlotItemId).collect(Collectors.toList());
        memberSlotItemDao.delete(member_id, slotitem_ids);
    }

    @Override
    public MemberSlotItemDestoryResult destroyItemAndReturnResource(String member_id, List<Long> slotitem_ids) {
        int[] getMaterials = new int[4];
        for (Long slotitem_id : slotitem_ids) {
            MemberSlotItem slotitem = getMemberSlotItem(member_id, slotitem_id);
            if (slotitem == null || slotitem.getLocked())
                throw new IllegalStateException();
            int[] broken = slotitem.getSlotItem().getBrokenArray();
            for (int i = 0; i < broken.length; i++) {
                getMaterials[i] += broken[i];
            }
        }
        memberSlotItemDao.delete(member_id, slotitem_ids);
        memberResourceService.increaseMaterial(member_id, getMaterials);
        return new MemberSlotItemDestoryResult(getMaterials);
    }
}
