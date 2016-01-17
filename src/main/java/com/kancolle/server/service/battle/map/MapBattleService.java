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
        int travellerNo = 10 * mapareaId + mapinfoNo;

        MemberDeckPort deckPort = memberDeckPortService.getUnNullableMemberDeckPort(member_id, deckId);

        MapTraveller traveller = loadMapTraveller(travellerNo);
        AbstractMapCell mapPoint = traveller.getStartPoint();

        MapStartResult result = mapPoint.getMapCellResult();
        int mapCellId = result.getApi_no();

        memberMapBattleMapper.insertMemberMapBattleState(member_id, deckId, travellerNo, mapCellId);

        return result;
    }

    @Override
    public MapStartResult next(String member_id, int recoverType) {

        MemberMapBattleState state = memberMapBattleMapper.selectMemberMapBattleState(member_id);

        MemberDeckPort deckPort = state.getMemberDeckPort();


        int travellerNo = state.getMapAreaId();
        int mapCellId = state.getMapCellId();

        MapTraveller traveller = loadMapTraveller(travellerNo);
        traveller.setMapCell(mapCellId);

        AbstractMapCell mapPoint =  traveller.getCurrentMapCell();

        return mapPoint.getMapCellResult();
    }

    private MapTraveller loadMapTraveller(int travellerNo) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        String travellerBeanName = String.format("map%dTraveller", travellerNo);
        return context.getBean(travellerBeanName, MapTraveller.class);
    }
}
