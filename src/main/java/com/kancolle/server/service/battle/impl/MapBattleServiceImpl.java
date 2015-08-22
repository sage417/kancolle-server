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
import com.kancolle.server.mapper.map.MemberMapBattleMapper;
import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.service.battle.MapBattleService;
import com.kancolle.server.service.deckport.MemberDeckPortService;
import com.kancolle.server.service.map.MapTraveller;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;

/**
 * @author J.K.SAGE
 * @Date 2015年8月20日
 *
 */
@Service
public class MapBattleServiceImpl implements MapBattleService {

    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @Autowired
    private MemberMapBattleMapper memberMapBattleMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public MapStartResult start(String member_id, MapStartForm form) {

        Integer deck_id = form.getApi_deck_id();
        int mapArea_id = form.getApi_maparea_id();
        int map_no = form.getApi_mapinfo_no();

        memberDeckPortService.getUnNullableMemberDeckPort(member_id, deck_id);

        memberMapBattleMapper.insertMemberMapBattleState(member_id, deck_id, mapArea_id, map_no);

        MapTraveller traveller = ContextLoader.getCurrentWebApplicationContext().getBean(String.format("map%d%dTraveller", mapArea_id, map_no), MapTraveller.class);
        AbstractMapCell mapPoint = traveller.getStartPoint();
        return mapPoint.getMapCellInfo();
    }
}
