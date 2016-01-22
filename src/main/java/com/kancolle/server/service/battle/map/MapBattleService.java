/**
 *
 */
package com.kancolle.server.service.battle.map;

import com.kancolle.server.controller.kcsapi.battle.form.MapStartForm;
import com.kancolle.server.mapper.map.MemberMapBattleMapper;
import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.po.battle.MemberMapBattleState;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.service.deckport.MemberDeckPortService;
import com.kancolle.server.service.map.MapTraveller;
import com.kancolle.server.service.map.MemberMapService;
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
    private MemberMapService memberMapService;

    @Autowired
    private MemberMapBattleMapper memberMapBattleMapper;

    /**
     * start表示从起始点出发到下一点的过程
     * 起始点no=0
     * 当前点的位置为下一点的位置
     *
     * @param member_id
     * @param form
     * @return
     */
    @Override
    public MapStartResult start(String member_id, MapStartForm form) {

        Integer deckId = form.getApi_deck_id();
        int mapareaId = form.getApi_maparea_id();
        int mapinfoNo = form.getApi_mapinfo_no();
        int travellerNo = 10 * mapareaId + mapinfoNo;

        MemberDeckPort deckPort = memberDeckPortService.getUnNullableMemberDeckPort(member_id, deckId);

        MapTraveller traveller = loadMapTraveller(travellerNo);

        MapStartResult result = traveller.start(deckPort);

        int mapCellId = traveller.getCurrentMapCell().getMapCellId();

        memberMapBattleMapper.insertMemberMapBattleState(member_id, deckId, travellerNo, mapCellId);

        updateMemberMapCell(member_id, mapCellId);

        return result;
    }

    @Override
    public MapNextResult next(String member_id, int recoverType) {

        MemberMapBattleState state = memberMapBattleMapper.selectMemberMapBattleState(member_id);

        int travellerNo = state.getMapAreaId();
        int mapCellId = state.getMapCellId();

        MapTraveller traveller = loadMapTraveller(travellerNo);
        traveller.setMapCell(mapCellId);

        MemberDeckPort deckPort = state.getMemberDeckPort();

        updateMemberMapCell(member_id, mapCellId);

        return traveller.next(deckPort);
    }

    private MapTraveller loadMapTraveller(int travellerNo) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        String travellerBeanName = String.format("map%dTraveller", travellerNo);
        return context.getBean(travellerBeanName, MapTraveller.class);
    }

    private void updateMemberMapCell(String memberId, int memberMapCellId) {
        memberMapService.updateMemberCellPassFlag(memberId, memberMapCellId, true);
    }
}
