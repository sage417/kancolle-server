package com.kancolle.server.service.mission.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.controller.kcsapi.form.mission.MissionStartForm;
import com.kancolle.server.dao.mission.MissionDao;
import com.kancolle.server.model.kcsapi.misson.MissionResult;
import com.kancolle.server.model.kcsapi.misson.MissionReturn;
import com.kancolle.server.model.kcsapi.misson.MissionStart;
import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.model.po.mission.Mission;
import com.kancolle.server.service.map.MapAreaService;
import com.kancolle.server.service.member.MemberDeckPortService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.mission.MissionService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.utils.DateUtils;

@Service
public class MissionServiceImpl implements MissionService {

    public static final int MISSION_PROCESSING = 1;

    public static final int MISSION_COMPLETE = 2;

    public static final int MISSION_RETURNING = 3;

    public static final int MISSION_FLAG = 0;

    @Autowired
    private MissionDao missionDao;

    @Autowired
    private MapAreaService mapAreaService;

    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberShipService memberShipSerivce;

    @Override
    public MissionReturn callbackMission(String member_id, int deck_id) {
        Instant now = Instant.now();

        MemberDeckPort deckport = memberDeckPortService.getMemberDeckPort(member_id, deck_id);
        int mission_id = (int) deckport.getMission()[1];

        long mission_complete_longtime = deckport.getMission()[2];

        Instant mission_complete_instant = Instant.ofEpochMilli(mission_complete_longtime);
        int mission_time = getMission(mission_id).getTime();
        Instant mission_start_instant = mission_complete_instant.plus(-mission_time, ChronoUnit.MINUTES);

        // 返回所需时间为远征出发时间和远征剩余时间小的一个的1/3
        long return_longtime = Math.min(mission_complete_longtime - now.toEpochMilli(), now.toEpochMilli() - mission_start_instant.toEpochMilli()) / 3;
        long mission_return_longtime = now.plus(return_longtime, ChronoUnit.MILLIS).toEpochMilli();

        deckport.getMission()[2] = mission_return_longtime;
        memberDeckPortService.updateDeckPortMission(deckport);

        return new MissionReturn(new long[] { MISSION_RETURNING, mission_id, mission_return_longtime, MISSION_FLAG });
    }

    @Override
    public MissionResult calMissionResult(String member_id, int deck_id) {
        /*
         * MemberDeckPort memberDeckPort =
         * memberDeckPortService.getMemberDeckPort(member_id, deck_id);
         * 
         * int mission_id = (int)memberDeckPort.getMission()[1];
         * 
         * MissionModel mission = missionService.getMission(mission_id);
         * 
         * MapAreaModel mapArea =
         * mapAreaService.getMapArea(mission.getApi_maparea_id());
         * 
         * Member member = memberService.getBasic(member_id);
         * 
         * long[] ship_ids = new long[memberDeckPort.getShip().length + 1];
         * ship_ids[0] =-1; for (int i = 1; i < ship_ids.length; i++) {
         * ship_ids[i] = memberDeckPort.getShip()[i-1]; }
         * 
         * MissionResult result = new MissionResult();
         * result.setApi_ship_id(ship_ids);
         * result.setApi_maparea_name(mapArea.getApi_name());
         * result.setApi_detail(mission.getApi_details());
         * result.setApi_quest_name(mission.getApi_name());
         * 
         * 
         * if (true) { // 召回 result.setApi_clear_result(RESULT_FAILED);
         * result.setApi_get_exp(0); result.setApi_member_lv(member.getLevel());
         * result.setApi_member_exp(member.getExperience()); int[] get_ship_exps
         * = new int[ship_ids.length - 1]; Arrays.fill(get_ship_exps, 0);
         * result.setApi_get_ship_exp(get_ship_exps);
         * 
         * long[][] api_get_exp_lvups = new long[ship_ids.length - 1][2];
         * 
         * for (int i = 1; i < ship_ids.length; i++) { long ship_id =
         * ship_ids[i]; api_get_exp_lvups[i - 1] =
         * memberShipSerivce.increaseMemberShipExp(member_id, ship_id,
         * get_ship_exps[i-1]); }
         * 
         * result.setApi_get_exp_lvup(api_get_exp_lvups);
         * 
         * result.setApi_get_material(new int[] { 0, 0, 0, 0 });
         * result.setApi_useitem_flag(new int[] { 0, 0 }); } else if (true) { //
         * 失敗 result.setApi_clear_result(RESULT_FAILED); MissionExp missionExp =
         * memberShipSerivce.getMissionExp(deckPortMission.getMissionId());
         * result.setApi_get_exp(missionExp.getExp()); member =
         * memberShipSerivce.updateMemberExpAndLevel(member_id,
         * missionExp.getExp()); result.setApi_member_lv(member.getLevel());
         * result.setApi_member_exp(member.getExperience());
         * 
         * int get_ship_exp = missionExp.getShipExp(); int[] get_ship_exps = new
         * int[ship_ids.length - 1]; Arrays.fill(get_ship_exps, get_ship_exp);
         * get_ship_exps[0] = get_ship_exps[0] * 3 / 2;
         * result.setApi_get_ship_exp(get_ship_exps);
         * 
         * long[][] api_get_exp_lvups = new long[ship_ids.length - 1][2];
         * 
         * for (int i = 1; i < ship_ids.length; i++) { long ship_id =
         * ship_ids[i]; api_get_exp_lvups[i - 1] =
         * memberShipSerivce.increaseMemberShipExp(member_id, ship_id,
         * get_ship_exps[i-1]); }
         * 
         * result.setApi_get_exp_lvup(api_get_exp_lvups);
         * 
         * result.setApi_get_material(new int[] { 0, 0, 0, 0 });
         * result.setApi_useitem_flag(new int[] { 0, 0 }); } else if (true) { //
         * 成功 result.setApi_clear_result(RESULT_SUCCESS); MissionExp missionExp
         * = memberShipSerivce.getMissionExp(deckPortMission.getMissionId());
         * result.setApi_get_exp(missionExp.getExp()); member =
         * memberShipSerivce.updateMemberExpAndLevel(member_id,
         * missionExp.getExp()); result.setApi_member_lv(member.getLevel());
         * result.setApi_member_exp(member.getExperience());
         * 
         * int get_ship_exp = missionExp.getShipExp(); int[] get_ship_exps = new
         * int[ship_ids.length - 1]; Arrays.fill(get_ship_exps, get_ship_exp);
         * get_ship_exps[0] = get_ship_exps[0] * 3 / 2;
         * result.setApi_get_ship_exp(get_ship_exps);
         * 
         * long[][] api_get_exp_lvups = new long[ship_ids.length - 1][2];
         * 
         * for (int i = 1; i < ship_ids.length; i++) { long ship_id =
         * ship_ids[i]; api_get_exp_lvups[i - 1] =
         * memberShipSerivce.increaseMemberShipExp(member_id, ship_id,
         * get_ship_exps[i-1]); }
         * 
         * result.setApi_get_exp_lvup(api_get_exp_lvups);
         * 
         * result.setApi_get_material(new int[] {
         * missionModel.getApi_win_item1().getIntValue(1),
         * missionModel.getApi_win_item1().getIntValue(2),
         * missionModel.getApi_win_item2().getIntValue(1),
         * missionModel.getApi_win_item2().getIntValue(2) });
         * result.setApi_useitem_flag(new int[] { 0, 0 }); } else { // 大成功
         * result.setApi_clear_result(RESULT_GREAT_SUCCESS); MissionExp
         * missionExp =
         * memberShipSerivce.getMissionExp(deckPortMission.getMissionId());
         * result.setApi_get_exp(missionExp.getExp()); member =
         * memberShipSerivce.updateMemberExpAndLevel(member_id,
         * missionExp.getExp()); result.setApi_member_lv(member.getLevel());
         * result.setApi_member_exp(member.getExperience());
         * 
         * int get_ship_exp = missionExp.getShipExp(); int[] get_ship_exps = new
         * int[ship_ids.length - 1]; Arrays.fill(get_ship_exps, get_ship_exp);
         * get_ship_exps[0] = get_ship_exps[0] * 3 / 2;
         * result.setApi_get_ship_exp(get_ship_exps);
         * 
         * long[][] api_get_exp_lvups = new long[ship_ids.length - 1][2];
         * 
         * for (int i = 1; i < ship_ids.length; i++) { long ship_id =
         * ship_ids[i]; api_get_exp_lvups[i - 1] =
         * memberShipSerivce.increaseMemberShipExp(member_id, ship_id,
         * get_ship_exps[i-1]); }
         * 
         * result.setApi_get_exp_lvup(api_get_exp_lvups);
         * 
         * result.setApi_get_material(new int[] {
         * missionModel.getApi_win_item1().getIntValue(1) * 3 / 2,
         * missionModel.getApi_win_item1().getIntValue(2) * 3 / 2,
         * missionModel.getApi_win_item2().getIntValue(1) 3 / 2,
         * missionModel.getApi_win_item2().getIntValue(2) 3 / 2 });
         * result.setApi_useitem_flag(new int[] { 0, 1 }); }
         */

        return null;
    }

    @Override
    public Mission getMission(Integer mission_id) {
        return missionDao.selectMission(mission_id);
    }

    @Override
    public MissionStart startMission(String member_id, MissionStartForm form) {
        Integer deck_id = form.getApi_deck_id();
        Integer mission_id = form.getApi_mission_id();
        // TODO
        Integer mission_hash = form.getApi_mission();

        Instant now = Instant.now();

        int mission_time = getMission(mission_id).getTime();
        Instant mission_complete_instant = now.plus(mission_time, ChronoUnit.MINUTES);

        long mission_complete_longtime = mission_complete_instant.toEpochMilli();

        MemberDeckPort deckport = memberDeckPortService.getMemberDeckPort(member_id, deck_id);

        long[] mission = deckport.getMission();
        mission[0] = MISSION_PROCESSING;
        mission[1] = mission_id;
        mission[2] = mission_complete_longtime;
        mission[3] = MISSION_FLAG;

        memberDeckPortService.updateDeckPortMission(deckport);

        return new MissionStart(mission_complete_longtime, DateUtils.format(mission_complete_instant));
    }
}
