package com.kancolle.server.service.mission.impl;

import static com.kancolle.server.model.kcsapi.misson.MissionResult.RESULT_FAILED;
import static com.kancolle.server.model.kcsapi.misson.MissionResult.RESULT_GREAT_SUCCESS;
import static com.kancolle.server.model.kcsapi.misson.MissionResult.RESULT_SUCCESS;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kancolle.server.dao.mission.MissionDao;
import com.kancolle.server.model.kcsapi.member.MemberBasic;
import com.kancolle.server.model.kcsapi.member.MemberDeckPort;
import com.kancolle.server.model.kcsapi.misson.MissionResult;
import com.kancolle.server.model.kcsapi.misson.MissionReturn;
import com.kancolle.server.model.kcsapi.misson.MissionStart;
import com.kancolle.server.model.kcsapi.start.sub.MapAreaModel;
import com.kancolle.server.model.kcsapi.start.sub.MissionModel;
import com.kancolle.server.model.po.DeckPortMission;
import com.kancolle.server.model.po.MissionExp;
import com.kancolle.server.service.deck.DeckPortService;
import com.kancolle.server.service.exp.ExpService;
import com.kancolle.server.service.map.MapAreaService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.mission.MissionService;

@Service
public class MissionServiceImpl implements MissionService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final int MISSION_PROCESSING = 1;

    public static final int MISSION_COMPLETE = 2;

    public static final int MISSION_RETURNING = 3;

    public static final int MISSION_FLAG = 0;

    @Autowired
    private MissionDao<?> missionDao;

    @Autowired
    private MapAreaService mapAreaService;

    @Autowired
    private DeckPortService deckPortService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ExpService expService;

    @Override
    public MissionReturn callbackMission(String member_id, int deck_id) {
        Instant now = Instant.now();

        DeckPortMission deckMission = this.getDeckPortMission(member_id, deck_id);
        int mission_id = deckMission.getMissionId();
        long mission_complete_longtime = deckMission.getMission_complete_time();

        int mission_time = this.getMission(mission_id).getApi_time();
        Instant mission_start_instant = now.plus(-mission_time, ChronoUnit.MINUTES);

        // 返回所需时间为远征出发时间和远征剩余时间小的一个的1/3
        long return_longtime = Math.min(mission_complete_longtime - now.getEpochSecond(), now.getEpochSecond() - mission_start_instant.getEpochSecond()) / 3;
        long mission_return_longtime = now.plus(return_longtime, ChronoUnit.SECONDS).getEpochSecond();

        DeckPortMission deckPortMission = new DeckPortMission(MISSION_RETURNING, mission_id, mission_return_longtime, MISSION_FLAG);
        this.updateDeckPortMission(member_id, deck_id, deckPortMission);

        return new MissionReturn(new long[] { MISSION_RETURNING, mission_id, mission_return_longtime, MISSION_FLAG });
    }

    @Override
    public MissionResult calMissionResult(String member_id, int deck_id) {
        MemberDeckPort memberDeckPort = deckPortService.getMemberDeckPort(member_id, deck_id);

        DeckPortMission deckPortMission = this.getDeckPortMission(member_id, deck_id);
        MissionModel missionModel = this.getMission(deckPortMission.getMissionId());

        MapAreaModel mapAreaModel = mapAreaService.getMapArea(missionModel.getApi_maparea_id());

        MemberBasic memberBasic = memberService.getBasic(member_id);

        long[] ship_ids = new long[memberDeckPort.size() + 1];
        ship_ids[0] = -1;
        for (int i = 1; i < ship_ids.length; i++) {
            ship_ids[i] = memberDeckPort.getApi_ship().getLongValue(i - 1);
        }

        MissionResult result = new MissionResult();
        result.setApi_ship_id(ship_ids);
        result.setApi_maparea_name(mapAreaModel.getApi_name());
        result.setApi_detail(missionModel.getApi_details());
        result.setApi_quest_name(missionModel.getApi_name());

        /*if (condition) {
            // 召回
            result.setApi_clear_result(RESULT_FAILED);
            result.setApi_get_exp(0);
            result.setApi_member_lv(memberBasic.getApi_level());
            result.setApi_member_exp(memberBasic.getApi_experience());
            int[] get_ship_exps = new int[ship_ids.length - 1];
            Arrays.fill(get_ship_exps, 0);
            result.setApi_get_ship_exp(get_ship_exps);

            long[][] api_get_exp_lvups = new long[ship_ids.length - 1][2];

            for (int i = 1; i < ship_ids.length; i++) {
                long ship_id = ship_ids[i];
                api_get_exp_lvups[i - 1] = expService.updateShipExpAndLevel(member_id, ship_id, get_ship_exps[i-1]);
            }

            result.setApi_get_exp_lvup(api_get_exp_lvups);

            result.setApi_get_material(new int[] { 0, 0, 0, 0 });
            result.setApi_useitem_flag(new int[] { 0, 0 });
        } else if (condition) {
            // 失敗
            result.setApi_clear_result(RESULT_FAILED);
            MissionExp missionExp = expService.getMissionExp(deckPortMission.getMissionId());
            result.setApi_get_exp(missionExp.getExp());
            memberBasic = expService.updateMemberExpAndLevel(member_id, missionExp.getExp());
            result.setApi_member_lv(memberBasic.getApi_level());
            result.setApi_member_exp(memberBasic.getApi_experience());

            int get_ship_exp = missionExp.getShipExp();
            int[] get_ship_exps = new int[ship_ids.length - 1];
            Arrays.fill(get_ship_exps, get_ship_exp);
            get_ship_exps[0] = get_ship_exps[0] * 3 / 2;
            result.setApi_get_ship_exp(get_ship_exps);
            
            long[][] api_get_exp_lvups = new long[ship_ids.length - 1][2];

            for (int i = 1; i < ship_ids.length; i++) {
                long ship_id = ship_ids[i];
                api_get_exp_lvups[i - 1] = expService.updateShipExpAndLevel(member_id, ship_id, get_ship_exps[i-1]);
            }

            result.setApi_get_exp_lvup(api_get_exp_lvups);

            result.setApi_get_material(new int[] { 0, 0, 0, 0 });
            result.setApi_useitem_flag(new int[] { 0, 0 });
        } else if (condition) {
            // 成功
            result.setApi_clear_result(RESULT_SUCCESS);
            MissionExp missionExp = expService.getMissionExp(deckPortMission.getMissionId());
            result.setApi_get_exp(missionExp.getExp());
            memberBasic = expService.updateMemberExpAndLevel(member_id, missionExp.getExp());
            result.setApi_member_lv(memberBasic.getApi_level());
            result.setApi_member_exp(memberBasic.getApi_experience());

            int get_ship_exp = missionExp.getShipExp();
            int[] get_ship_exps = new int[ship_ids.length - 1];
            Arrays.fill(get_ship_exps, get_ship_exp);
            get_ship_exps[0] = get_ship_exps[0] * 3 / 2;
            result.setApi_get_ship_exp(get_ship_exps);
            
            long[][] api_get_exp_lvups = new long[ship_ids.length - 1][2];

            for (int i = 1; i < ship_ids.length; i++) {
                long ship_id = ship_ids[i];
                api_get_exp_lvups[i - 1] = expService.updateShipExpAndLevel(member_id, ship_id, get_ship_exps[i-1]);
            }

            result.setApi_get_exp_lvup(api_get_exp_lvups);

            result.setApi_get_material(new int[] { missionModel.getApi_win_item1().getIntValue(1), missionModel.getApi_win_item1().getIntValue(2), missionModel.getApi_win_item2().getIntValue(1), missionModel.getApi_win_item2().getIntValue(2) });
            result.setApi_useitem_flag(new int[] { 0, 0 });
        } else {
            // 大成功
            result.setApi_clear_result(RESULT_GREAT_SUCCESS);
            MissionExp missionExp = expService.getMissionExp(deckPortMission.getMissionId());
            result.setApi_get_exp(missionExp.getExp());
            memberBasic = expService.updateMemberExpAndLevel(member_id, missionExp.getExp());
            result.setApi_member_lv(memberBasic.getApi_level());
            result.setApi_member_exp(memberBasic.getApi_experience());

            int get_ship_exp = missionExp.getShipExp();
            int[] get_ship_exps = new int[ship_ids.length - 1];
            Arrays.fill(get_ship_exps, get_ship_exp);
            get_ship_exps[0] = get_ship_exps[0] * 3 / 2;
            result.setApi_get_ship_exp(get_ship_exps);
            
            long[][] api_get_exp_lvups = new long[ship_ids.length - 1][2];

            for (int i = 1; i < ship_ids.length; i++) {
                long ship_id = ship_ids[i];
                api_get_exp_lvups[i - 1] = expService.updateShipExpAndLevel(member_id, ship_id, get_ship_exps[i-1]);
            }

            result.setApi_get_exp_lvup(api_get_exp_lvups);

            result.setApi_get_material(new int[] { missionModel.getApi_win_item1().getIntValue(1) * 3 / 2, missionModel.getApi_win_item1().getIntValue(2) * 3 / 2, missionModel.getApi_win_item2().getIntValue(1) * 3 / 2, missionModel.getApi_win_item2().getIntValue(2) * 3 / 2 });
            result.setApi_useitem_flag(new int[] { 0, 1 });
        }*/

        return result;
    }

    @Override
    public DeckPortMission getDeckPortMission(String member_id, int deck_id) {
        return missionDao.getDeckPortMission(member_id, deck_id);
    }

    @Cacheable(value = "mission", key = "#mission_id")
    @Override
    public MissionModel getMission(int mission_id) {
        return missionDao.getMission(mission_id);
    }

    @Override
    public MissionStart startMission(String member_id, int deck_id, int mission_id) {
        Instant now = Instant.now();

        int mission_time = this.getMission(mission_id).getApi_time();
        Instant mission_complete_instant = now.plus(mission_time, ChronoUnit.MINUTES);

        long mission_complete_longtime = mission_complete_instant.getEpochSecond();

        DeckPortMission deckPortMission = new DeckPortMission(MISSION_PROCESSING, mission_id, mission_complete_longtime, MISSION_FLAG);

        this.updateDeckPortMission(member_id, deck_id, deckPortMission);

        return new MissionStart(mission_complete_longtime, FORMATTER.format(LocalDateTime.ofInstant(mission_complete_instant, ZoneId.systemDefault())));
    }

    @Override
    public int updateDeckPortMission(String member_id, int deck_id, DeckPortMission deckPortMission) {
        return missionDao.updateDeckPortMission(member_id, deck_id, deckPortMission);
    }
}
