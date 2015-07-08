package com.kancolle.server.service.mission.impl;

import static com.kancolle.server.model.kcsapi.misson.MissionResult.RESULT_FAILED;
import static com.kancolle.server.model.kcsapi.misson.MissionResult.RESULT_GREAT_SUCCESS;
import static com.kancolle.server.model.kcsapi.misson.MissionResult.RESULT_SUCCESS;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.ImmutableList;
import com.kancolle.server.controller.kcsapi.form.mission.MissionStartForm;
import com.kancolle.server.dao.mission.MissionDao;
import com.kancolle.server.model.kcsapi.misson.MissionResult;
import com.kancolle.server.model.kcsapi.misson.MissionReturn;
import com.kancolle.server.model.kcsapi.misson.MissionStart;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.po.member.MemberDeckPort;
import com.kancolle.server.model.po.mission.Mission;
import com.kancolle.server.model.po.mission.MissionExp;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.member.MemberDeckPortService;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.mission.MissionResultChecker;
import com.kancolle.server.service.mission.MissionService;
import com.kancolle.server.service.mission.utils.MissionCondResult;
import com.kancolle.server.service.mission.utils.MissionUtils;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.utils.DateUtils;

@Service
public class MissionServiceImpl implements MissionService {

    private static final int MISSION_PROCESSING = 1;

    private static final int MISSION_COMPLETE = 2;

    private static final int MISSION_RETURNING = 3;

    private static final long MISSION_FLAG = 0L;

    private static final int[] EMPTY_RESOURCE_ARRAY = new int[] { 0, 0, 0, 0 };

    @Autowired
    private MissionDao missionDao;

    @Autowired
    private MemberDeckPortService memberDeckPortService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberShipService memberShipSerivce;

    @Override
    public List<Mission> getMissions() {
        return missionDao.selectMissions();
    }

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

        deckport.getMission()[0] = MISSION_RETURNING;
        deckport.getMission()[2] = mission_return_longtime;
        memberDeckPortService.updateDeckPortMission(deckport);

        return new MissionReturn(new long[] { MISSION_RETURNING, mission_id, mission_return_longtime, MISSION_FLAG });
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public MissionResult calMissionResult(String member_id, Integer deck_id) {
        MemberDeckPort deckport = memberDeckPortService.getMemberDeckPort(member_id, deck_id);
        if (deckport == null) {
            throw new IllegalArgumentException();
        }
        Mission mission = getMission((int) deckport.getMission()[1]);
        if (mission == null) {
            throw new IllegalArgumentException();
        }
        // 这里舰队阵容不会改变，复制一份进行操作
        List<MemberShip> deck_ships = ImmutableList.copyOf(deckport.getShips());

        MissionResult result = new MissionResult(mission);
        result.setApi_ship_id(ArrayUtils.add(deckport.getShip(), 0, -1L));

        MissionExp missionExp = mission.getMissionExp();
        int ship_exp = missionExp.getShipExp();
        int member_exp = missionExp.getMemberExp();

        MissionCondResult mr = MissionResultChecker.getMissionResultChecker(mission.getMissionId()).getResult(deckport);

        switch (mr) {
        case CALL_BACK:
            result.setApi_clear_result(RESULT_FAILED);
            result.setApi_get_exp(0);
            result.setApi_get_ship_exp(MissionUtils.getShipExps(deckport, 0));
            result.setApi_get_material(EMPTY_RESOURCE_ARRAY);
            break;
        case FAIL:
            result.setApi_clear_result(RESULT_FAILED);
            result.setApi_get_exp(member_exp);
            memberService.increaseMemberExp(memberService.getMember(member_id), member_exp);
            missionIncreaseMemberShipExp(deck_ships, ship_exp);
            result.setApi_get_ship_exp(MissionUtils.getShipExps(deckport, ship_exp));
            result.setApi_get_material(EMPTY_RESOURCE_ARRAY);
            break;
        case SUCCESS:
            // 远征结果标志位
            result.setApi_clear_result(RESULT_SUCCESS);
            // 提督经验
            result.setApi_get_exp(member_exp);
            memberService.increaseMemberExp(memberService.getMember(member_id), member_exp);
            missionIncreaseMemberShipExp(deck_ships, ship_exp);
            result.setApi_get_ship_exp(MissionUtils.getShipExps(deckport, ship_exp));
            result.setApi_get_material(mission.getMaterials());
            break;
        case GREATE_SUCCESS:
            result.setApi_clear_result(RESULT_GREAT_SUCCESS);
            result.setApi_get_exp(member_exp);
            memberService.increaseMemberExp(memberService.getMember(member_id), member_exp);
            missionIncreaseMemberShipExp(deck_ships, ship_exp);
            result.setApi_get_ship_exp(MissionUtils.getShipExps(deckport, ship_exp));
            result.setApi_get_material(IntStream.of(mission.getMaterials()).map(value -> value * 3 / 2).toArray());
            break;
        default:
            throw new IllegalArgumentException();
        }
        /*-------计算提督经验情况---------*/
        Member member = memberService.getMember(member_id);
        result.setApi_member_lv(member.getLevel());
        result.setApi_member_exp(member.getExperience());
        /*-------计算提督经验情况---------*/

        /*---------计算舰娘经验情况------------*/
        int deck_ship_size = deck_ships.size();
        long[][] ship_exp_lvup = new long[deck_ship_size][];
        IntStream.iterate(0, i -> i + 1).limit(deck_ship_size).forEach(i -> ship_exp_lvup[i] = ArrayUtils.subarray(deck_ships.get(i).getExp(), 0, 2));
        result.setApi_get_exp_lvup(ship_exp_lvup);
        /*---------计算舰娘经验情况------------*/

        /*--------- 最后清除舰队的远征信息------------*/
        Arrays.fill(deckport.getMission(), 0L);
        memberDeckPortService.updateDeckPortMission(deckport);
        /*--------- 最后清除舰队的远征信息------------*/

        return result;
    }

    private void missionIncreaseMemberShipExp(List<MemberShip> deck_ships, int exp) {
        // 旗舰获得1.5倍经验值
        memberShipSerivce.increaseMemberShipExp(deck_ships.get(0), exp * 3 / 2);
        // 其余获得正常经验值
        deck_ships.stream().skip(1L).forEach(memberShip -> memberShipSerivce.increaseMemberShipExp(memberShip, exp));
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
