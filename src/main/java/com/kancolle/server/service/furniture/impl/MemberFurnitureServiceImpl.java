/**
 *
 */
package com.kancolle.server.service.furniture.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureBuyForm;
import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureChangeForm;
import com.kancolle.server.dao.furniture.MemberFurnitureDao;
import com.kancolle.server.model.po.furniture.Furniture;
import com.kancolle.server.model.po.furniture.FurnitureType;
import com.kancolle.server.model.po.furniture.MemberFurniture;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.service.furniture.MemberFurnitureService;
import com.kancolle.server.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.kancolle.server.model.po.furniture.FurnitureType.*;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 */
@Service
public class MemberFurnitureServiceImpl implements MemberFurnitureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberFurnitureServiceImpl.class);

    @Autowired
    private MemberFurnitureDao memberFurnitureDao;
    @Autowired
    private MemberService memberService;

    private static final ImmutableMap<FurnitureType, Function<FurnitureChangeForm, Integer>> typeToFurnitureId =
            new ImmutableMap.Builder<FurnitureType, Function<FurnitureChangeForm, Integer>>()
                    .put(FLOOR, FurnitureChangeForm::getApi_floor)
                    .put(WALLPAPER, FurnitureChangeForm::getApi_wallpaper)
                    .put(WINDOW, FurnitureChangeForm::getApi_window)
                    .put(WALLHANGING, FurnitureChangeForm::getApi_wallhanging)
                    .put(SHELF, FurnitureChangeForm::getApi_shelf)
                    .put(DESK, FurnitureChangeForm::getApi_desk)
                    .build();


    private Integer getFurnitureIdByType(FurnitureChangeForm form, FurnitureType type) {
        return typeToFurnitureId.get(type).apply(form);
    }


    @Override
    public List<MemberFurniture> getFurniture(String member_id) {
        return memberFurnitureDao.getFurniture(member_id);
    }

    @Override
    public void changeFurniture(final String member_id, final FurnitureChangeForm form) {
        final FurnitureType[] furnitureTypes = FurnitureType.values();

        int[] furnitureIds = Arrays.stream(furnitureTypes).mapToInt(type -> {
            Integer furnitureId = getFurnitureIdByType(form, type);
            MemberFurniture memberFurniture = getMemberFurniture(member_id, furnitureId);
            Preconditions.checkNotNull(memberFurniture, "尝试使用不存在或未购买的家具, furniture_id:%d", furnitureId);
            final int typeId = type.getTypeId();
            final int furnitureTypeId = memberFurniture.getFurniture().getType();
            Preconditions.checkArgument(typeId == furnitureTypeId,
                    "设置家具类型对应错误,furniture_id:%d, set_type:%d, actual_type:%d", furnitureId, typeId, furnitureTypeId);
            return furnitureId;
        }).toArray();

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
            createMemberFurniture(member_id, furnitureId);
        } catch (RuntimeException e) {
            LOGGER.warn("Id:{} 购买家具失败，原因：{}", member_id, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void createMemberFurniture(String member_id, Integer furniture_id) {
        memberFurnitureDao.insertMemberFurniture(member_id, furniture_id);
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

    @Override
    public void initMemberFurniture(long member_id) {
        memberFurnitureDao.insertFurnituresForNewMember(member_id);
    }
}
