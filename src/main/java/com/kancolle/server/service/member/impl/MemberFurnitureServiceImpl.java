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
            furnitureIds.add(furnitureId);
            memberFurnitureDao.checkFurnitureType(member_id, furnitureId, type);
        }

        memberFurnitureDao.checkFurnituresExist(member_id, furnitureIds);

        memberFurnitureDao.changeFurniture(member_id, furnitureIds);
    }

    @Transactional
    @Override
    public void buyFurniture(String member_id, FurnitureBuyForm form) {
        Furniture furniture = memberFurnitureDao.getFurnitureByTypeAndNo(form.getApi_type(), form.getApi_no());

        if (furniture == null) {
            // TODO
            throw new NullPointerException("家具不存在");
        }

        Member member = memberService.getMember(Long.valueOf(member_id));
        if (member.getfCoin() < furniture.getPrice()) {
            // TODO
            throw new IllegalArgumentException("家具币不足");
        }

        memberFurnitureDao.checkFurnitureBeforeBuy(member_id, furniture.getFurnitureId());

        if (furniture.getPrice() > 0) {
            memberFurnitureDao.costMemberFurnitureCoin(member_id, furniture.getPrice());
        }

        memberFurnitureDao.addMemberFurniture(member_id, furniture.getFurnitureId());
    }
}
