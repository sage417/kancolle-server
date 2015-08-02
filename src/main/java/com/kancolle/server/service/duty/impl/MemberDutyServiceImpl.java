package com.kancolle.server.service.duty.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.kancolle.server.model.po.duty.Duty.OPERATE_TYPE_POWUP;
import static com.kancolle.server.model.po.duty.MemberDuty.STATE_AVILABLE;
import static com.kancolle.server.model.po.duty.MemberDuty.STATE_FINISH;
import static com.kancolle.server.model.po.duty.MemberDuty.STATE_PROCESSING;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.kancolle.server.dao.duty.MemberDutyDao;
import com.kancolle.server.model.event.PowUpEvent;
import com.kancolle.server.model.kcsapi.duty.DutyBouns;
import com.kancolle.server.model.kcsapi.duty.DutyItemGetResult;
import com.kancolle.server.model.kcsapi.duty.MemberDutyList;
import com.kancolle.server.model.po.duty.Duty;
import com.kancolle.server.model.po.duty.MemberDuty;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.MemberSlotItem;
import com.kancolle.server.model.po.useitem.UseItem;
import com.kancolle.server.service.duty.DutyResultChecker;
import com.kancolle.server.service.duty.MemberDutyService;
import com.kancolle.server.service.furniture.MemberFurnitureService;
import com.kancolle.server.service.member.MemberDeckPortService;
import com.kancolle.server.service.member.MemberResourceService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.slotitem.MemberSlotItemService;
import com.kancolle.server.service.useitem.MemberUseItemService;
import com.kancolle.server.service.useitem.UseItemService;

@Service
public class MemberDutyServiceImpl implements MemberDutyService {

    @Autowired
    public void register(EventBus eventBus) {
        eventBus.register(this);
    }

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

    @Override
    public MemberDutyList getMemberDutyList(String member_id, int pageNum) {
        int count_of_duty_om_process = memberDutyDao.selectCountOfMemberDutysByState(member_id, STATE_PROCESSING);
        return new MemberDutyList((Page<MemberDuty>) memberDutyDao.selectMemberDutys(member_id, pageNum, 5), count_of_duty_om_process);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberDuty getMemberDuty(String member_id, Integer quest_id) {
        return checkNotNull(memberDutyDao.selectMemberDutyByCond(member_id, quest_id));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public List<MemberDuty> getMemberDutysByState(String member_id, int state) {
        return memberDutyDao.selectMembersDutyByState(member_id, state);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void start(String member_id, Integer quest_id) {
        MemberDuty memberDuty = getMemberDuty(member_id, quest_id);
        checkState(memberDuty.getState() == STATE_AVILABLE);
        memberDuty.setState(STATE_PROCESSING);
        memberDutyDao.update(memberDuty);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void stop(String member_id, Integer quest_id) {
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
            DutyResultChecker checker = ContextLoader.getCurrentWebApplicationContext().getBean(String.format("duty%dResultChecker", memberDuty.getDutyNo()), DutyResultChecker.class);
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

    private void addResource(int[] increaseItems, int[] winItem) {
        int count = winItem[1];
        switch (winItem[0]) {
        case 5:
            increaseItems[1] = count;
            break;
        case 6:
            increaseItems[0] = count;
            break;
        case 7:
            increaseItems[2] = count;
            break;
        case 8:
            increaseItems[3] = count;
            break;
        default:
            break;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public DutyItemGetResult clearitemget(String member_id, Integer quest_id) {
        MemberDuty memberDuty = getMemberDuty(member_id, quest_id);
        Duty duty = memberDuty.getDuty();

        List<DutyBouns> api_bounus = Lists.newArrayListWithCapacity(2);
        int[] increaseItems = { 0, 0, 0, 0 };

        switch (duty.getBonusFlag()) {
        case Duty.BONUS_TYPE_RESOURCE:
            // 5高速建造,6高速修复,7开发紫菜,8修改资材
        case Duty.BONUS_TYPE_ITEM:
        case Duty.BONUS_TYPE_FURNITUREBOX:
            int[][] winItemArrays = { duty.getWinItem1(), duty.getWinItem2() };

            for (int[] winItem : winItemArrays) {
                if (winItem[0] != 0 && winItem[1] > 0) {
                    Integer useitem_id = Integer.valueOf(winItem[0]);
                    UseItem useitem = useItemService.getUseItemById(useitem_id);
                    if (duty.getBonusFlag() == Duty.BONUS_TYPE_RESOURCE) {
                        addResource(increaseItems, winItem);
                    } else {
                        memberUseItemService.addMemberUseItemCount(member_id, useitem.getUseitemId() - 4, winItem[1]);
                    }
                    api_bounus.add(new DutyBouns(duty.getBonusFlag(), winItem[1], winItem[0], useitem.getName()));
                }
            }
            break;
        case Duty.BONUS_TYPE_DECKPORT:
            Integer deckport_id = Integer.valueOf(duty.getBonusItemId());
            memberDeckPortService.openDeckPort(member_id, deckport_id);
            break;
        case Duty.BONUS_TYPE_LARGEBUILD:
            memberService.openLargeBuild(member_id);
            break;
        case Duty.BONUS_TYPE_SHIP:
            MemberShip ship = memberShipService.createShip(member_id, duty.getBonusItemId());
            api_bounus.add(new DutyBouns(duty.getBonusFlag(), 1, duty.getBonusItemId(), StringUtils.EMPTY));
            break;
        case Duty.BONUS_TYPE_SLOT:
            MemberSlotItem slotitem = memberSlotItemService.createSlotItem(member_id, duty.getBonusItemId());
            api_bounus.add(new DutyBouns(duty.getBonusFlag(), 1, duty.getBonusItemId(), StringUtils.EMPTY));
            break;
        case Duty.BONUS_TYPE_FURNITURE:
            Integer furniture_id = Integer.valueOf(duty.getBonusItemId());
            memberFurnitureService.createMemberFurniture(member_id, furniture_id);
            api_bounus.add(new DutyBouns(duty.getBonusFlag(), 1, duty.getBonusItemId(), StringUtils.EMPTY));
            break;
        case Duty.BONUS_TYPE_FLIGHT:
            break;
        default:
            break;
        }
        // 獲得獎勵
        memberResourceService.increaseMaterial(member_id, duty.getMaterial(), increaseItems);
        // 刪除完成任務
        // memberDutyDao.deleteDuty(memberDuty);
        // 插入後續任務
        // memberDutyDao.insertAfterDutys(memberDuty);
        return new DutyItemGetResult(duty.getMaterial(), api_bounus.size(), api_bounus);
    }
}
