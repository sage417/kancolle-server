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
import com.kancolle.server.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author J.K.SAGE
 * @Date 2015年8月20日
 */
@Service
public class MapBattleService {

    public static final String BATTLE_FLAG = "battleFlag";

    public static final String RESULT_FLAG = "resultFlag";

    public static final String MAPCELL_ID = "mapCellId";

    public static final String SESSION = "session";

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
    @Transactional
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

        updateMemberMapCellInfo(member_id, mapCellId);

        return result;
    }

    @Transactional
    public MapNextResult next(String member_id, int recoverType) {

        MemberMapBattleState state = memberMapBattleMapper.selectMemberMapBattleState(member_id);

        int travellerNo = state.getTravellerNo();
        int mapCellId = state.getMapCellId();

        MapTraveller traveller = loadMapTraveller(travellerNo);
        traveller.setMapCell(mapCellId);

        MemberDeckPort deckPort = state.getMemberDeckPort();

        updateMemberMapCellInfo(member_id, mapCellId);

        MapNextResult result = traveller.next(deckPort);

        int nextMapCellId = traveller.getCurrentMapCell().getMapCellId();

        state.setMapCellId(nextMapCellId);
        memberMapBattleMapper.update(state, MAPCELL_ID);

        return result;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberMapBattleState selectMemberMapBattleState(String member_id){
        return memberMapBattleMapper.selectMemberMapBattleState(member_id);
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void updateMemberMapBattleStatus(MemberMapBattleState state, String... columns) {
        memberMapBattleMapper.update(state, columns);
    }

    private MapTraveller loadMapTraveller(int travellerNo) {
        String travellerBeanName = String.format("map%dTraveller", travellerNo);
        return SpringUtils.getBean(travellerBeanName, MapTraveller.class);
    }

    private void updateMemberMapCellInfo(String memberId, int memberMapCellId) {
        memberMapService.updateMemberCellPassFlag(memberId, memberMapCellId, true);
    }
}
