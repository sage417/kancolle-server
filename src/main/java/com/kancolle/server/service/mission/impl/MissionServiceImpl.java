package com.kancolle.server.service.mission.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kancolle.server.controller.kcsapi.form.mission.MissionStartForm;
import com.kancolle.server.dao.mission.MissionDao;
import com.kancolle.server.model.kcsapi.misson.MissionResult;
import com.kancolle.server.model.kcsapi.misson.MissionReturn;
import com.kancolle.server.model.kcsapi.misson.MissionStart;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.model.po.mission.Mission;
import com.kancolle.server.service.map.MapAreaService;
import com.kancolle.server.service.member.MemberDeckPortService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.mission.MissionResultChecker;
import com.kancolle.server.service.mission.MissionService;
import com.kancolle.server.service.mission.utils.MissionCondResult;
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
    public MissionResult calMissionResult(String member_id, Integer deck_id) {
        MemberDeckPort deckport = memberDeckPortService.getMemberDeckPort(member_id, deck_id);
        if (deckport == null) {
            throw new IllegalArgumentException();
        }
        Mission mission = getMission((int) deckport.getMission()[1]);
        if (mission == null) {
            throw new IllegalArgumentException();
        }

        MissionResult result = new MissionResult(mission);
        result.setApi_ship_id(ArrayUtils.add(deckport.getShip(), 0, -1L));

        MissionCondResult mr = MissionResultChecker.getMissionResultChecker(mission.getMissionId()).getResult(deckport);

        switch (mr) {
        case CALL_BACK:
            result.setApi_clear_result(-1);
            break;
        case FAIL:
            result.setApi_clear_result(-1);
            // result.setApi_get_exp(api_get_exp);
            // memberService.increaseMemberExp(memberService.getMember(member_id),
            // exp);
            // memberShipSerivce.increaseMemberShipExp(deckport.getShips().get(0),
            // exp);
            // deckport.getShips().stream().skip(1L).forEach(memberShip ->
            // memberShipSerivce.increaseMemberShipExp(memberShip, exp));
            // result.setApi_get_material(mission.getme);
            break;
        case SUCCESS:
            result.setApi_clear_result(1);
            break;
        case GREATE_SUCCESS:
            result.setApi_clear_result(2);
            break;
        default:
            break;
        }
        Member member = memberService.getMember(member_id);
        result.setApi_member_lv(member.getLevel());
        result.setApi_member_exp(member.getExperience());
        deckport = memberDeckPortService.getMemberDeckPort(member_id, deck_id);
        long[][] api_get_exp_lvup = new long[deckport.getShips().size()][];
        for (int i = 0; i < api_get_exp_lvup.length; i++) {
            api_get_exp_lvup[i] = ArrayUtils.subarray(deckport.getShips().get(i).getExp(), 0, 2);
        }
        result.setApi_get_exp_lvup(api_get_exp_lvup);
        return result;
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

    @Override
    public List<Mission> getMissions() {
        return missionDao.selectMissions();
    }
}
