/**
 *
 */
package com.kancolle.server.service.battle.map;

import com.kancolle.server.controller.kcsapi.battle.form.MapStartForm;
import com.kancolle.server.mapper.map.MemberMapBattleMapper;
import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.po.battle.MemberMapBattleState;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.service.deckport.MemberDeckPortService;
import com.kancolle.server.service.map.MapTraveller;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author J.K.SAGE
 * @Date 2015年8月20日
 */
@Service
public class MapBattleService implements IMapBattleService {

    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @Autowired
    private MemberMapBattleMapper memberMapBattleMapper;

    @Override
    public MapStartResult start(String member_id, MapStartForm form) {

        Integer deckId = form.getApi_deck_id();
        int mapareaId = form.getApi_maparea_id();
        int mapinfoNo = form.getApi_mapinfo_no();

        MemberDeckPort deckPort = memberDeckPortService.getUnNullableMemberDeckPort(member_id, deckId);

        MapTraveller traveller = loadMapTraveller(mapareaId, mapinfoNo);
        AbstractMapCell mapPoint = traveller.getStartPoint();

        memberMapBattleMapper.insertMemberMapBattleState(member_id, deckId, mapareaId, mapinfoNo);

        return mapPoint.getMapCellInfo();
    }

    @Override
    public MapStartResult next(String member_id, int recoverType) {

        MemberMapBattleState state = memberMapBattleMapper.selectMemberMapBattleState(member_id);

        MemberDeckPort deckPort = state.getMemberDeckPort();
        state.getMapAreaId();
        state.getMapCellId();

        return null;
    }

    private MapTraveller getMapTraveller(int mapareaId, int mapNo) {
        MapTraveller traveller = loadMapTraveller(mapareaId, mapNo);
        traveller.reset();
        return traveller;
    }

    private MapTraveller getMapTraveller(int mapareaId, int mapNo, int mapCellId) {
        MapTraveller traveller = loadMapTraveller(mapareaId, mapNo);
        traveller.setMapCell(mapCellId);
        return traveller;
    }

    private MapTraveller loadMapTraveller(int mapareaId, int mapNo) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        String travellerBeanName = String.format("map%d%dTraveller", mapareaId, mapNo);
        return context.getBean(travellerBeanName, MapTraveller.class);
    }
}
