/**
 * 
 */
package com.kancolle.server.service.furniture.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureBuyForm;
import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureChangeForm;
import com.kancolle.server.dao.furniture.MemberFurnitureDao;
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.model.po.furniture.FurnitureType;
import com.kancolle.server.model.po.furniture.MemberFurniture;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.service.furniture.MemberFurnitureService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.utils.logic.FurnitureUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
@Service
public class MemberFurnitureServiceImpl implements MemberFurnitureService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberFurnitureServiceImpl.class);

    @Autowired
    private MemberFurnitureDao memberFurnitureDao;

    @Autowired
    private MemberService memberService;

    @Override
    public List<MemberFurniture> getFurniture(String member_id) {
        return memberFurnitureDao.getFurniture(member_id);
    }

    @Override
    public void changeFurniture(String member_id, FurnitureChangeForm form) {
        List<Integer> furnitureIds = Lists.newArrayListWithCapacity(FurnitureType.values().length);

        for (FurnitureType type : FurnitureType.values()) {
            Integer furnitureId = FurnitureUtils.getFurnitureIdByType(form, type);
            MemberFurniture memberFurniture = getMemberFurniture(member_id, furnitureId);
            if (memberFurniture == null) {
                throw new IllegalArgumentException("不拥有该家具");
            }
            if (type.getTypeId() != memberFurniture.getFurniture().getType()) {
                throw new IllegalArgumentException("家具类型错误");
            }
            furnitureIds.add(furnitureId);
        }

        memberFurnitureDao.changeMemberFurniture(member_id, furnitureIds);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public void buyFurniture(String member_id, FurnitureBuyForm form) {

        Integer furnitureType = form.getApi_type();
        Integer furnitureNo = form.getApi_no();

        Furniture furniture = getFurniture(furnitureType, furnitureNo);
        if (furniture == null) {
            LOGGER.warn("用户Id:{} 查询不存在的家具，type = {}, no = {}", member_id, furnitureType, furnitureNo);
            throw new IllegalArgumentException("家具不存在");
        }

        Integer furnitureId = furniture.getFurnitureId();

        if (getMemberFurniture(member_id, furnitureId) != null) {
            LOGGER.warn("用户Id:{} 购买已拥有的家具，furnitureId = {}", member_id, furnitureId);
            throw new IllegalArgumentException("已拥有该家具");
        }

        int price = furniture.getPrice();
        if (price > 0) {
            Member member = memberService.getMember(member_id);

            int remainFCoin = member.getfCoin() - price;

            if (remainFCoin < 0) {
                LOGGER.warn("用户Id:{} 家具币不足，furnitureId = {}", member_id, furnitureId);
                throw new IllegalArgumentException("家具币不足");
            }
            member.setfCoin(remainFCoin);
            memberService.updateMember(member);
        }

        try {
            memberFurnitureDao.insertMemberFurniture(member_id, furnitureId);
        } catch (RuntimeException e) {
            LOGGER.warn("Id:{} 购买家具失败，原因：{}", member_id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberFurniture getMemberFurniture(String member_id, Integer furniture_id) {
        return memberFurnitureDao.selectMemberFurnitureById(member_id, furniture_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.SUPPORTS)
    public Furniture getFurniture(Integer type, Integer no) {
        return memberFurnitureDao.selectFurnitureByTypeAndNo(type, no);
    }

    @Override
    public int getCountOfMemberFurniture(String member_id) {
        return memberFurnitureDao.selectCountOfMemberFurniture(member_id);
    }
}
