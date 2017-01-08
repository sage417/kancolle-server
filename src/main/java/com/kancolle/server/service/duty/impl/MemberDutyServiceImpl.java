package com.kancolle.server.service.duty.impl;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.kancolle.server.dao.duty.MemberDutyDao;
import com.kancolle.server.model.event.PowUpEvent;
import com.kancolle.server.model.kcsapi.duty.DutyBonusResult;
import com.kancolle.server.model.kcsapi.duty.DutyItemGetResult;
import com.kancolle.server.model.kcsapi.duty.MemberDutyPageList;
import com.kancolle.server.model.po.duty.Duty;
import com.kancolle.server.model.po.duty.DutyBonus;
import com.kancolle.server.model.po.duty.MemberDuty;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.model.po.useitem.UseItem;
import com.kancolle.server.service.deckport.MemberDeckPortService;
import com.kancolle.server.service.duty.DutyResultChecker;
import com.kancolle.server.service.duty.MemberDutyService;
import com.kancolle.server.service.furniture.MemberFurnitureService;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.slotitem.MemberSlotItemService;
import com.kancolle.server.service.useitem.MemberUseItemService;
import com.kancolle.server.service.useitem.UseItemService;
import com.kancolle.server.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;
import static com.kancolle.server.model.po.duty.Duty.OPERATE_TYPE_POWUP;
import static com.kancolle.server.model.po.duty.MemberDuty.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
public class MemberDutyServiceImpl implements MemberDutyService {
    @Autowired
    private MemberDutyDao memberDutyDao;
    @Autowired
    private MemberResourceService memberResourceService;
    @Autowired
    private UseItemService useItemService;
    @Autowired
    private MemberUseItemService memberUseItemService;
    @Autowired
    private MemberDeckPortService memberDeckPortService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberShipService memberShipService;
    @Autowired
    private MemberSlotItemService memberSlotItemService;
    @Autowired
    private MemberFurnitureService memberFurnitureService;
    @Autowired
    @Qualifier("dutyBus")
    private EventBus dutyBus;

    @PostConstruct
    private void init() {
        this.dutyBus.register(this);
    }

    @Override
    public MemberDutyPageList getMemberDutyList(long member_id, int pageNum) {
        int count_of_duty_om_process = memberDutyDao.selectCountOfMemberDutysByState(member_id, STATE_PROCESSING);
        return new MemberDutyPageList((Page<MemberDuty>) memberDutyDao.selectMemberDutys(member_id, pageNum, 5), count_of_duty_om_process);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberDuty getMemberDuty(long member_id, Integer quest_id) {
        return checkNotNull(memberDutyDao.selectMemberDutyByCond(member_id, quest_id));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<MemberDuty> getMemberDutysByState(long member_id, int state) {
        return memberDutyDao.selectMembersDutyByState(member_id, state);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void start(long member_id, Integer quest_id) {
        MemberDuty memberDuty = getMemberDuty(member_id, quest_id);
        checkState(memberDuty.getState() == STATE_AVILABLE);
        memberDuty.setState(STATE_PROCESSING);
        memberDutyDao.update(memberDuty);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void stop(long member_id, Integer quest_id) {
        MemberDuty memberDuty = getMemberDuty(member_id, quest_id);
        checkState(memberDuty.getState() == STATE_PROCESSING);
        memberDuty.setState(STATE_AVILABLE);
        memberDutyDao.update(memberDuty);
    }

    @Override
    @Subscribe
    public void listenPowUpEvent(PowUpEvent event) {
        List<MemberDuty> accecptDutys = getMemberDutysByState(event.getMember_id(), STATE_PROCESSING);
        List<MemberDuty> eventDutys = accecptDutys.stream().filter(memberDuty -> memberDuty.getDuty().getOperate() == OPERATE_TYPE_POWUP).collect(Collectors.toList());
        for (MemberDuty memberDuty : eventDutys) {
            memberDuty.setCounter(memberDuty.getCounter() + 1);
            DutyResultChecker checker = SpringUtils.getBean(String.format("duty%dResultChecker", memberDuty.getDutyNo()), DutyResultChecker.class);
            if (checker.checkCond(memberDuty)) {
                // 任务完成
                memberDuty.setProgressFlag(0);
                memberDuty.setState(STATE_FINISH);
            } else {
                checker.updateProgressFlag(memberDuty);
            }
            memberDutyDao.update(memberDuty);
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public DutyItemGetResult clearitemget(long member_id, Integer quest_id) {
        MemberDuty memberDuty = getMemberDuty(member_id, quest_id);
        Duty duty = memberDuty.getDuty();

        List<DutyBonusResult> api_bounus = Lists.newArrayListWithCapacity(2);
        int[] increaseItems = { 0, 0, 0, 0 };

        for (DutyBonus bonus : duty.getDutyBonus()) {
            int itemId = bonus.getItemId();
            int count = bonus.getCount();
            int bonusType = bonus.getType();

            switch (bonusType) {
            case Duty.BONUS_TYPE_RESOURCE:
                if (count > 0) {
                    UseItem useitem = useItemService.getUseItemById(itemId);
                    checkNotNull(useitem);
                    api_bounus.add(new DutyBonusResult(bonusType, count, itemId, useitem.getName()));
                    // 5高速建造,6高速修复,7开发紫菜,8修改资材
                    switch (itemId) {
                    case 5:
                        increaseItems[1] += count;
                        break;
                    case 6:
                        increaseItems[0] += count;
                        break;
                    case 7:
                        increaseItems[2] += count;
                        break;
                    case 8:
                        increaseItems[3] += count;
                        break;
                    default:
                        break;
                    }
                }
                break;
            case Duty.BONUS_TYPE_OPEN_DECK:
                memberDeckPortService.openDeckPort(member_id, itemId);
                api_bounus.add(new DutyBonusResult(bonusType, 0, 0, String.format("第%d艦隊", itemId)));
                break;
            case Duty.BONUS_TYPE_FURNITUREBOX:
                if (count > 0) {
                    UseItem useitem = useItemService.getUseItemById(itemId);
                    api_bounus.add(new DutyBonusResult(bonusType, count, itemId, useitem.getName()));
                    memberUseItemService.addMemberUseItemCount(member_id, itemId, count);
                }
                break;
            case Duty.BONUS_TYPE_LARGEBUILD:
                memberService.openLargeDock(member_id);
                api_bounus.add(new DutyBonusResult(bonusType, 0, 0, EMPTY));
                break;
            case Duty.BONUS_TYPE_SHIP:
                for (int i = 0; i < count; i++) {
                    MemberShip ship = memberShipService.createShip(member_id, itemId);
                    checkNotNull(ship);
                }
                api_bounus.add(new DutyBonusResult(bonusType, count, itemId));
                break;
            case Duty.BONUS_TYPE_SLOTITEM:
                for (int i = 0; i < count; i++) {
                    MemberSlotItem slotitem = memberSlotItemService.createSlotItem(member_id, itemId);
                    checkNotNull(slotitem);
                }
                api_bounus.add(new DutyBonusResult(bonusType, count, itemId, EMPTY));
                break;
            case Duty.BONUS_TYPE_USEITEM:
                memberUseItemService.addMemberUseItemCount(member_id, itemId, count);
                api_bounus.add(new DutyBonusResult(bonusType, count, itemId, EMPTY));
                break;
            case Duty.BONUS_TYPE_FURNITURE:
                checkArgument(count == 1);
                memberFurnitureService.createMemberFurniture(member_id, itemId);
                api_bounus.add(new DutyBonusResult(bonusType, count, itemId, EMPTY));
                break;
            case Duty.BONUS_TYPE_MODEL_CHANGE:
                break;
            default:
                break;
            }
        }

        // 獲得獎勵
        memberResourceService.increaseMaterial(member_id, duty.getMaterial(), increaseItems);
        // 刪除完成任務
        memberDutyDao.deleteDuty(memberDuty);
        // 插入後續任務
        memberDutyDao.insertAfterDutys(memberDuty);
        return new DutyItemGetResult(duty.getMaterial(), api_bounus.size(), api_bounus);
    }
}
