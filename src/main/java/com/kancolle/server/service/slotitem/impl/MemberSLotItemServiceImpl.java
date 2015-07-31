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

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kancolle.server.controller.kcsapi.form.item.CreateItemForm;
import com.kancolle.server.dao.slotitem.MemberSlotItemDao;
import com.kancolle.server.model.kcsapi.slotitem.CreateItemResult;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemDestoryResult;
import com.kancolle.server.model.kcsapi.slotitem.MemberSlotItemLockResult;
import com.kancolle.server.model.po.common.ResourceValue;
import com.kancolle.server.model.po.resource.Resource;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.model.po.slotitem.SlotItem;
import com.kancolle.server.service.member.MemberDeckPortService;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.slotitem.MemberSlotItemService;
import com.kancolle.server.service.slotitem.SlotItemService;
import com.kancolle.server.utils.NumberArrayUtils;

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
    private MemberService memberService;

    @Autowired
    private MemberResourceService memberResourceService;

    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public CreateItemResult createItem(String member_id, CreateItemForm form) {
        int fuel = form.getApi_item1();
        int bull = form.getApi_item2();
        int steel = form.getApi_item3();
        int baxuite = form.getApi_item4();

        if (memberService.getMember(member_id).getMaxSlotItem() == getCountOfMemberSlotItem(member_id))
            throw new IllegalStateException();

        MemberShip leaderShip = memberDeckPortService.getMemberDeckPort(member_id, Integer.valueOf(1)).getShips().get(0);

        SlotItem targetSlotItem = null;

        List<Integer> slotItemTypes = slotItemService.getSllotItemTypeCanDevelop(leaderShip.getShip().getType());
        List<SlotItem> slotItems = slotItemService.getSlotItemsCanDevelop(slotItemTypes.get(RandomUtils.nextInt(0, slotItemTypes.size())));
        int rare_count = slotItems.stream().mapToInt(slot -> 6 - slot.getRare()).sum();
        int randomValue = RandomUtils.nextInt(0, rare_count);
        for (SlotItem slotItem : slotItems) {
            int weight = 6 - slotItem.getRare();
            if (randomValue <= weight) {
                targetSlotItem = slotItem;
                break;
            }
            randomValue = randomValue - weight;
        }

        boolean success = true;

        ResourceValue broken = targetSlotItem.getBroken();

        do{
            int slotitem_id = targetSlotItem.getSlotItemId();

            if (slotitem_id == 9 && (bull <= fuel || bull <= steel || bull <= baxuite)) {
                success = false;
                break;
            }

            if (slotitem_id == 37 && (baxuite > fuel || baxuite > bull || baxuite > steel)) {
                success = false;
                break;
            }

            if (slotitem_id == 60 && (baxuite < bull || baxuite <= fuel || baxuite <= steel)) {
                success = false;
                break;
            }

            if (slotitem_id == 59 && (baxuite < fuel || baxuite < bull || steel < fuel || steel < bull)) {
                success = false;
                break;
            }
            if (slotitem_id == 45 && (bull < baxuite || bull <= fuel || bull <= steel)) {
                success = false;
                break;
            }

            if (slotitem_id == 47 && (baxuite <= fuel || baxuite <= bull || baxuite <= steel)) {
                success = false;
                break;
            }
            
            if (slotitem_id == 72) {
                int max = NumberArrayUtils.max(fuel, bull, steel, baxuite);
                if (max != fuel && max != steel) {
                    success = false;
                    break;
                }
                success = false;
                break;
            }

            if (fuel < broken.getFuel() * 10) {
                success = false;
                break;
            }
            if (bull < broken.getBull() * 10) {
                success = false;
                break;
            }
            if (steel < broken.getSteel() * 10) {
                success = false;
                break;
            }
            if (baxuite < broken.getBaxuite() * 10) {
                success = false;
                break;
            }

        } while (false);

        memberResourceService.consumeResource(member_id, fuel, bull, steel, baxuite, 0, 0, success ? 1 : 0, 0);
        Resource memberResource = memberResourceService.getMemberResouce(member_id);

        if (success) {
            MemberSlotItem createItem = createSlotItem(member_id, targetSlotItem.getSlotItemId());
            List<MemberSlotItem> unsetSlots = getUnsetSlotList(member_id);
            long[] api_unsetslot = unsetSlots.stream().filter(slotItem -> slotItem.getSlotItem().getType()[2] == createItem.getSlotItem().getType()[2]).mapToLong(MemberSlotItem::getMemberSlotItemId)
                    .toArray();
            return new CreateItemResult(CreateItemResult.CREATE_SUCCESS, 1, createItem, memberResource, createItem.getSlotItem().getType()[2], api_unsetslot);
        } else {
            return new CreateItemResult(CreateItemResult.CREATE_FAIL, 0, "2," + targetSlotItem.getSlotItemId(), memberResource);
        }
    }

    @Override
    public MemberSlotItem createSlotItem(String member_id, int slotitem_id) {
        return memberSlotItemDao.createMemberSlotItem(member_id, slotitem_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.SUPPORTS)
    public void destorySlotitems(String member_id, List<MemberSlotItem> removeSlotitems) {
        for (MemberSlotItem memberSlotItem : removeSlotitems) {
            if (memberSlotItem == null || memberSlotItem.getLocked())
                throw new IllegalStateException();
        }
        List<Long> slotitem_ids = removeSlotitems.stream().map(MemberSlotItem::getMemberSlotItemId).collect(Collectors.toList());
        memberSlotItemDao.delete(member_id, slotitem_ids);
    }

    /**
     * 废弃装备需要满足两个条件<br>
     * 1. 没有上锁<br>
     * 2. 没有被舰娘装备<br>
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public MemberSlotItemDestoryResult destroyItemAndReturnResource(String member_id, List<Long> slotitem_ids) {

        List<MemberSlotItem> unSlots = getUnsetSlotList(member_id);

        int[] getMaterials = new int[4];

        for (Long slotitem_id : slotitem_ids) {
            MemberSlotItem slotitem = getMemberSlotItem(member_id, slotitem_id);
            if (slotitem == null || !unSlots.contains(slotitem) || slotitem.getLocked())
                throw new IllegalStateException();
            NumberArrayUtils.arraySum(getMaterials, slotitem.getSlotItem().getBrokenArray());
        }
        memberSlotItemDao.delete(member_id, slotitem_ids);
        memberResourceService.increaseMaterial(member_id, getMaterials);
        return new MemberSlotItemDestoryResult(getMaterials);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberSlotItem getMemberSlotItem(String memberId, Long memberSlotItemId) {
        return memberSlotItemDao.selectMemberSlotItem(memberId, memberSlotItemId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<MemberSlotItem> getMemberSlotItems(String member_id) {
        return memberSlotItemDao.selectMemberSlotItems(member_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<MemberSlotItem> getUnsetSlotList(String member_id) {
        return memberSlotItemDao.selectMemberUnSlots(member_id);
    }

    @Override
    public Map<String, Object> getUnsetSlotMap(String member_id) {
        List<MemberSlotItem> unsetSlots = getUnsetSlotList(member_id);

        int slotTypeCount = slotItemService.getCountOfSlotItemTypes();

        Map<String, Object> unslotMap = new LinkedHashMap<String, Object>(slotTypeCount);

        Stream.iterate(1, n -> ++n).limit(slotTypeCount).forEach(i -> {
            List<Long> ids = unsetSlots.stream().filter(slotitem -> slotitem.getSlotItem().getType()[2] == i).map(MemberSlotItem::getMemberSlotItemId).collect(Collectors.toList());
            unslotMap.put(MessageFormat.format("api_slottype{0}", i), ids.isEmpty() ? -1 : ids);
        });
        return unslotMap;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
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
    public int getCountOfMemberSlotItem(String member_id) {
        return memberSlotItemDao.selectCountOfMemberSlotItem(member_id);
    }
}
