/**
 * 
 */
package com.kancolle.server.service.useitem.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kancolle.server.controller.kcsapi.form.item.UseItemForm;
import com.kancolle.server.dao.member.MemberUseItemDao;
import com.kancolle.server.model.kcsapi.useitem.UseItemResult;
import com.kancolle.server.model.kcsapi.useitem.item.FurnitureCoin;
import com.kancolle.server.model.kcsapi.useitem.item.GetItem;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.po.useitem.MemberUseItem;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.useitem.MemberUseItemService;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 *
 */
@Service
public class MemberUseItemServiceImpl implements MemberUseItemService {

    private static final List<Integer> FURNITURE_COIN_BOX = Lists.newArrayList(10, 11, 12);

    @Autowired
    private MemberUseItemDao memberUseItemDao;

    @Autowired
    private MemberService memberService;

    @Override
    public List<MemberUseItem> getMemberUseItems(String member_id) {
        return memberUseItemDao.selectMemberUseItems(member_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public UseItemResult useItem(String member_id, UseItemForm form) {
        Integer forceFlag = form.getApi_force_flag();
        Integer useItemId = form.getApi_useitem_id();

        UseItemResult result = new UseItemResult(0, 1);
        GetItem getItem = null;

        // 家具币
        if (FURNITURE_COIN_BOX.contains(useItemId)) {
            int itemCount = getCountOfMemberUseItem(member_id, useItemId);

            if (useItemId == 10) {
                getItem = new FurnitureCoin(200 * itemCount);
            } else if (useItemId == 11) {
                getItem = new FurnitureCoin(400 * itemCount);
            } else if (useItemId == 12) {
                getItem = new FurnitureCoin(700 * itemCount);
            }
            Member member = memberService.getMember(member_id);
            member.setfCoin(member.getfCoin() + getItem.getApi_getcount());
            memberService.updateMember(member);
        }

        // 甲级徽章
        if (useItemId == 61) {

        }

        // 菱饼
        if (useItemId == 62) {

        }

        // 司令部要员
        if (useItemId == 63) {

        }

        result.setGetItem(getItem);
        return result;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public int getCountOfMemberUseItem(String member_id, Integer useItem_id) {
        return memberUseItemDao.countMemberItem(member_id, useItem_id);
    }

    @Override
    public void addMemberUseItemCount(String member_id, int useitem_id, int addCount) {
        memberUseItemDao.addMemberUseItemCount(member_id, useitem_id, addCount);
    }
}
