/**
 * 
 */
package com.kancolle.server.service.battle.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.kancolle.server.controller.kcsapi.battle.form.MapStartForm;
import com.kancolle.server.model.kcsapi.battle.MapStartResult;
import com.kancolle.server.service.battle.MapBattleService;
import com.kancolle.server.service.map.traveller.MapTraveller;
import com.kancolle.server.service.member.MemberDeckPortService;

/**
 * @author J.K.SAGE
 * @Date 2015年8月20日
 *
 */
@Service
public class MapBattleServiceImpl implements MapBattleService {

    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public MapStartResult start(String member_id, MapStartForm form) {

        Integer deck_id = form.getApi_deck_id();
        int mapArea_id = form.getApi_maparea_id();
        int map_no = form.getApi_mapinfo_no();
        memberDeckPortService.getUnNullableMemberDeckPort(member_id, deck_id);

        MapTraveller traveller = ContextLoader.getCurrentWebApplicationContext().getBean(String.format("map%d%dTraveller", mapArea_id, map_no), MapTraveller.class);
        MapStartResult result = traveller.getNext();
        return result;
    }
}
