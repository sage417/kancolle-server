/**
 *
 */
package com.kancolle.server.service.battle.map;

import com.kancolle.server.controller.kcsapi.battle.form.MapStartForm;
import com.kancolle.server.mapper.map.MemberMapBattleMapper;
import com.kancolle.server.model.kcsapi.battle.map.MapNextResult;
import com.kancolle.server.model.kcsapi.battle.map.MapStartResult;
import com.kancolle.server.model.mongo.BattleResult;
import com.kancolle.server.model.mongo.MemberBattleFleet;
import com.kancolle.server.model.po.battle.MemberMapBattleState;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.SlimDeckPort;
import com.kancolle.server.model.po.map.MapCellModel;
import com.kancolle.server.service.deckport.MemberDeckPortService;
import com.kancolle.server.service.map.MemberMapService;
import com.kancolle.server.service.map.mapcells.INormalMapCell;
import com.kancolle.server.service.map.traveller.MapTraveller;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private Map<String, MapTraveller> mapTravellers;
    @Autowired
    private Datastore datastore;

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
    public MapStartResult start(long member_id, MapStartForm form) {

        Integer deckId = form.getApi_deck_id();
        int mapareaId = form.getApi_maparea_id();
        int mapinfoNo = form.getApi_mapinfo_no();
        int travellerNo = 10 * mapareaId + mapinfoNo;

        MemberDeckPort deckPort = memberDeckPortService.getUnNullableMemberDeckPort(member_id, deckId);

        MapTraveller traveller = loadMapTraveller(travellerNo);

        MapStartResult result = traveller.start(deckPort, mapareaId, mapinfoNo);

        final MapCellModel mapCell = traveller.getToMapCell().getMapCell();
        final int mapCellId = mapCell.getApi_id();

        memberMapBattleMapper.insertMemberMapBattleState(member_id, deckId, travellerNo, mapCellId);

        updateMemberMapCellInfo(member_id, mapCellId);

        saveMemberBattleFleet(member_id, travellerNo, mapCell, deckId);

        return result;
    }

    private void saveMemberBattleFleet(long member_id, int traveller_no, MapCellModel map_cell, int... deck_ids) {
        MemberBattleFleet dbMemberBattleFleet = datastore.createQuery(MemberBattleFleet.class).field("member_id").equal(member_id).project("_id", true).get();

        MemberBattleFleet memberBattleFleet = new MemberBattleFleet();
        if (dbMemberBattleFleet != null) {
            memberBattleFleet.setId(dbMemberBattleFleet.getId());
        }

        memberBattleFleet.setMemberId(member_id);
        memberBattleFleet.setTravellerNo(traveller_no);
        memberBattleFleet.setMapCellNo(map_cell.getApi_id());
        memberBattleFleet.setMapCellName(map_cell.getName());

        List<SlimDeckPort> memberDeckPorts =
                Arrays.stream(deck_ids)
                        .mapToObj(deck_id -> memberDeckPortService.getEagerUnNullableMemberDeckPort(member_id, deck_id))
                        .collect(Collectors.toList());

        memberBattleFleet.setFleets(memberDeckPorts);

        memberBattleFleet.setBattleResult(new BattleResult());

        datastore.save(memberBattleFleet);
    }

    @Transactional
    public MapNextResult next(long member_id, int recoverType) {

        MemberMapBattleState state = memberMapBattleMapper.selectMemberMapBattleState(member_id);

        int travellerNo = state.getTravellerNo();
        int mapCellId = state.getMapCellId();

        MapTraveller traveller = loadMapTraveller(travellerNo);

        MemberDeckPort deckPort = state.getMemberDeckPort();

        MapNextResult result = traveller.next(mapCellId, deckPort);

        final INormalMapCell toMapCell = traveller.getToMapCell();
        int nextMapCellId = toMapCell.getMapCellId();

        updateMemberMapCellInfo(member_id, nextMapCellId);
        state.setMapCellId(nextMapCellId);
        memberMapBattleMapper.update(state, MAPCELL_ID);

        datastore.updateFirst(datastore.createQuery(MemberBattleFleet.class).field("member_id").equal(member_id),
                datastore.createUpdateOperations(MemberBattleFleet.class).set("map_cell_no", toMapCell.getMapCell().getApi_id()).set("map_cell_name", toMapCell.getMapCell().getName()));

        return result;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberMapBattleState selectMemberMapBattleState(long member_id) {
        return memberMapBattleMapper.selectMemberMapBattleState(member_id);
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void updateMemberMapBattleStatus(MemberMapBattleState state, String... columns) {
        memberMapBattleMapper.update(state, columns);
    }

    private MapTraveller loadMapTraveller(int travellerNo) {
        return mapTravellers.get(String.format("map%dTraveller", travellerNo));
    }

    private void updateMemberMapCellInfo(long member_id, int memberMapCellId) {
        memberMapService.updateMemberCellPassFlag(member_id, memberMapCellId, true);
    }
}
