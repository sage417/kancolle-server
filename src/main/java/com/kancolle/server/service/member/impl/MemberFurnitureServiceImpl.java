/**
 * 
 */
package com.kancolle.server.service.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.kancolle.server.controller.kcsapi.form.ChangeFurnitureForm;
import com.kancolle.server.dao.member.MemberFurnitureDao;
import com.kancolle.server.model.po.furniture.FurnitureType;
import com.kancolle.server.service.member.MemberFurnitureService;
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

    @Override
    public void changeFurniture(String member_id, ChangeFurnitureForm form) {

        List<Integer> furnitureIds = Lists.newArrayListWithCapacity(FurnitureType.values().length);
        for (FurnitureType type : FurnitureType.values()) {
            Integer furnitureId = FurnitureUtils.getFurnitureIdByType(form, type);
            furnitureIds.add(furnitureId);
            memberFurnitureDao.checkFurnitureType(member_id, furnitureId, type);
        }

        memberFurnitureDao.checkFurniture(member_id, furnitureIds);

        memberFurnitureDao.changeFurniture(member_id, furnitureIds);
    }
}
