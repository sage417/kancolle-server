/**
 * 
 */
package com.kancolle.server.service.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureBuyForm;
import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureChangeForm;
import com.kancolle.server.dao.member.MemberFurnitureDao;
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.model.po.furniture.FurnitureType;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.service.member.MemberFurnitureService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.utils.logic.FurnitureUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
@Service
public class MemberFurnitureServiceImpl implements MemberFurnitureService {
    @Autowired
    private MemberFurnitureDao memberFurnitureDao;

    @Autowired
    private MemberService memberService;

    @Override
    public void changeFurniture(String member_id, FurnitureChangeForm form) {
        List<Integer> furnitureIds = Lists.newArrayListWithCapacity(FurnitureType.values().length);

        for (FurnitureType type : FurnitureType.values()) {
            Integer furnitureId = FurnitureUtils.getFurnitureIdByType(form, type);
            Furniture furniture = memberFurnitureDao.selectMemberFurnitureById(member_id, furnitureId);
            if (furniture == null) {
                throw new IllegalArgumentException("不拥有该家具");
            }
            if (type.getTypeId() != furniture.getType()) {
                throw new IllegalArgumentException("家具类型错误");
            }
            furnitureIds.add(furnitureId);
        }

        memberFurnitureDao.changeMemberFurniture(member_id, furnitureIds);
    }

    @Override
    @Transactional
    public void buyFurniture(String member_id, FurnitureBuyForm form) {

        Furniture furniture = memberFurnitureDao.selectFurnitureByTypeAndNo(form.getApi_type(), form.getApi_no());
        if (furniture == null) {
            // TODO
            throw new NullPointerException("家具不存在");
        }

        Integer furnitureId = furniture.getFurnitureId();

        if (memberFurnitureDao.selectMemberFurnitureById(member_id, furnitureId) != null) {
            throw new IllegalArgumentException("已拥有该家具");
        }

        Member member = memberService.getMember(Long.valueOf(member_id));

        if (furniture.getPrice() > 0) {
            int remainFCoin = member.getfCoin() - furniture.getPrice();
            if (remainFCoin < 0) {
                throw new IllegalArgumentException("家具币不足");
            }
            member.setfCoin(remainFCoin);
            memberService.updateMember(member);
        }

        try {
            memberFurnitureDao.insertMemberFurniture(member_id, furnitureId);
        } catch (RuntimeException e) {
            String msg = e.getMessage();
            throw new RuntimeException("购买家具失败");
        }
    }
}
