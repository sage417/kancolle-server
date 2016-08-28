package com.kancolle.server.service.battle;
/**
 *
 */

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.math.DoubleMath;
import com.kancolle.server.controller.kcsapi.battle.form.BattleForm;
import com.kancolle.server.model.kcsapi.battle.BattleResult;
import com.kancolle.server.model.kcsapi.battle.BattleResult.*;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.kcsapi.battle.houku.KouKuResult;
import com.kancolle.server.model.kcsapi.battle.houku.KouKuStage1;
import com.kancolle.server.model.kcsapi.battle.houku.KouKuStage2;
import com.kancolle.server.model.kcsapi.battle.houku.KouKuStage3;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.kcsapi.start.sub.MapInfoModel;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.BattleSession;
import com.kancolle.server.model.po.battle.MemberMapBattleState;
import com.kancolle.server.model.po.common.MaxMinValue;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.UnderSeaShip;
import com.kancolle.server.service.base.BaseService;
import com.kancolle.server.service.battle.aerial.IAerialBattleSystem;
import com.kancolle.server.service.battle.course.ICourseSystem;
import com.kancolle.server.service.battle.map.MapBattleService;
import com.kancolle.server.service.battle.reconnaissance.IReconnaissanceAircraftSystem;
import com.kancolle.server.service.battle.shelling.template.ShellingTemplate;
import com.kancolle.server.service.deckport.UnderSeaDeckPortService;
import com.kancolle.server.service.map.impl.MapService;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.utils.SpringUtils;
import com.kancolle.server.utils.logic.ship.ShipFilter;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.kancolle.server.model.kcsapi.battle.BattleResult.*;
import static com.kancolle.server.service.battle.map.MapBattleService.*;
import static com.kancolle.server.utils.logic.DeckPortUtils.FIRST_SHELL_SHIP_ORDER;
import static com.kancolle.server.utils.logic.DeckPortUtils.getAttackShips;
import static com.kancolle.server.utils.logic.ship.ShipFilter.*;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 */
@Service
public class BattleService extends BaseService {

    @Autowired
    private MapBattleService mapBattleService;
    @Autowired
    private IReconnaissanceAircraftSystem reconnaissanceAircraftSystem;
    @Autowired
    private ICourseSystem courseSystem;
    @Autowired
    private IAerialBattleSystem aerialBattleSystem;
    @Autowired
    private ShellingTemplate<MemberShip, UnderSeaShip> memberShipShellingSystem;
    @Autowired
    private ShellingTemplate<UnderSeaShip, MemberShip> underSeaShipShellingSystem;
    @Autowired
    private MapService mapService;
    @Autowired
    private UnderSeaDeckPortService underSeaDeckPortService;
    @Autowired
    private ShipService shipService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberShipService memberShipService;

    public BattleSimulationResult battle(String member_id, BattleForm form) {
        MemberMapBattleState battleState = mapBattleService.selectMemberMapBattleState(member_id);
        checkState(!battleState.isBattleFlag());

        int formation = form.getApi_formation();
        /* 旗艦大破進撃フラグ 0=通常, 1=応急修理要員を利用して進撃?, 2=応急修理女神を利用して進撃? */
        int recovery_type = form.getApi_recovery_type();

        // create battle context
        BattleContext context = new BattleContext();

        MemberDeckPort memberDeckPort = checkNotNull(battleState.getMemberDeckPort());
        context.setMemberDeckPort(memberDeckPort);

        int mapCellId = battleState.getMapCellId();
        AbstractMapCell mapCell = SpringUtils.getBean(String.format("mapCell%d", mapCellId), AbstractMapCell.class);

        UnderSeaDeckPort underSeaDeckPort = mapCell.getUnderSeaDeckPort();
        context.setUnderSeaDeckPort(underSeaDeckPort);

        List<MemberShip> memberShips = memberDeckPort.getShips();
        List<UnderSeaShip> underSeaShips = underSeaDeckPort.getUnderSeaShips();

        // build ship idx map
        Map<Integer, IShip> memberShipMap = memberShips.stream().collect(Collectors.toMap(s -> 1 + memberShips.indexOf(s), s -> s));
        Map<Integer, IShip> underSeaShipMap = IntStream.range(0, underSeaShips.size()).boxed().collect(Collectors.toMap(i -> i + 7, underSeaShips::get));
        ImmutableBiMap<Integer, IShip> shipMap = new ImmutableBiMap.Builder<Integer, IShip>().putAll(memberShipMap).putAll(underSeaShipMap).build();
        context.setShipMap(shipMap);

        BattleSimulationResult result = new BattleSimulationResult(memberDeckPort, underSeaDeckPort);
        context.setBattleResult(result);

        // 判断航向
        int course = courseSystem.generateCourse();
        result.setApi_formation(new int[]{formation, underSeaDeckPort.getFormation(), course});

        // 制空权
        int memberAerialPower = aerialBattleSystem.getMemberDeckPortAerialPower(memberDeckPort.getShips());
        int underSeaAerialPower = aerialBattleSystem.getMemberDeckPortAerialPower(underSeaDeckPort.getUnderSeaShips());
        int memberAerialState = aerialBattleSystem.getAerialPowerStatue(memberAerialPower, underSeaAerialPower);
        int underSeaAerialState = aerialBattleSystem.getAerialPowerStatue(underSeaAerialPower, memberAerialPower);
        context.setMemberAerialState(memberAerialState);
        context.setUnderSeaAerialState(underSeaAerialState);

        /*------------------------1.索敌开始------------------------*/

        /** 我方索敌 */
        int fsResult = reconnaissanceAircraftSystem.memberDeckPortSearchEnemy(memberDeckPort, underSeaDeckPort, underSeaAerialState);
        /** 敌方索敌 */
        int esResult = reconnaissanceAircraftSystem.enemyDeckPortSearchMember(memberDeckPort, underSeaDeckPort);

        final int memberSakuteki = memberShips.stream().map(MemberShip::getSakuteki).mapToInt(MaxMinValue::getMinValue).sum();
        final int underSeaSakuteki = underSeaShips.stream().mapToInt(UnderSeaShip::getShipSakuteki).sum();
        context.setMemberSakuteki(memberSakuteki);
        context.setUnderSeaSakuteki(underSeaSakuteki);

        result.setApi_search(new int[]{fsResult, esResult});

        boolean memberSearchSuccess = reconnaissanceAircraftSystem.isSearchSuccess(fsResult);
        boolean enemySearchSuccess = reconnaissanceAircraftSystem.isSearchSuccess(esResult);
        /*-------------------------索敌结束------------------------*/

        /*------------------------2.航空战开始开始------------------------*/

        KouKuResult kouKuResult = new KouKuResult();
       // result.setApi_kouku(kouKuResult);
        /*-------制空战开始-------*/
        KouKuStage1 stage1 = new KouKuStage1();
        kouKuResult.setStage1(stage1);
        stage1.setApi_disp_seiku(underSeaAerialState);

        /*-------制空战结束-------*/

        /*------防空火炮迎击开始------*/
        KouKuStage2 stage2 = new KouKuStage2();
        kouKuResult.setStage2(stage2);

        /*------防空火炮迎击结束------*/

        /*-------航空接触开始-------*/

        /*-------航空接触结束-------*/

        /*-------攻击机、轰炸机的雷击、轰炸-----*/
        KouKuStage3 stage3 = new KouKuStage3();
        kouKuResult.setStage3(stage3);

        /*-------攻击机、轰炸机的雷击、轰炸结束-----*/

        /*-------------------------航空战开始结束-----------------------*/

        /*-------------------------3支援舰队达到------------------------*/

        /*--------------------------支援舰队返航------------------------*/

        /*--------------------------4.开幕雷击---------------------------*/

        /*--------------------------开幕雷击结束---------------------------*/

        /*--------------------------5.炮击战---------------------------*/

        boolean hasBB = memberShips.stream().anyMatch(BBShipFilter) || underSeaShips.stream().anyMatch(BBShipFilter);
        int[] api_hourai_flag = {1, hasBB ? 1 : 0, 0, 0};
        result.setApi_hourai_flag(api_hourai_flag);

        List<MemberShip> aliveMemberDefendShips = memberShips.stream().filter(ShipFilter.isAlive).collect(Collectors.toList());
        List<UnderSeaShip> aliveUnderSeaDefendShips = underSeaShips.stream().filter(ShipFilter.isAlive).collect(Collectors.toList());

        // 玩家潜艇队列，无法被攻击的潜艇将被移除
        List<MemberShip> aliveMemberSSShips = getTargetShips(aliveMemberDefendShips, ssFilter);
        context.setAliveMemberSSShips(aliveMemberSSShips);
        // 玩家非潜艇队列，无法被攻击的舰船将被移除
        List<MemberShip> aliveMemberNormalShips = getTargetShips(aliveMemberDefendShips, ssFilter.negate());
        context.setAliveMemberNormalShips(aliveMemberNormalShips);

        // 敌方潜艇队列，无法被攻击的潜艇将被移除
        List<UnderSeaShip> aliveUnderSeaSSShips = getTargetShips(aliveUnderSeaDefendShips, ssFilter);
        context.setAliveUnderSeaSSShips(aliveUnderSeaSSShips);
        // 敌方非潜艇队列，无法被攻击的舰船将被移除
        List<UnderSeaShip> aliveUnderSeaNormalShip = getTargetShips(aliveUnderSeaDefendShips, ssFilter.negate());
        context.setAliveUnderSeaNormalShips(aliveUnderSeaNormalShip);

        int shellingRound = hasBB ? 2 : 1;

        switch (shellingRound) {
            case 2:
                firstShellingRound(context);
                secondShellingRound(context);
                break;
            case 1:
                firstShellingRound(context);
                break;
            default:
                break;
        }

        /*--------------------------炮击战---------------------------*/

        /*--------------------------6.闭幕雷击---------------------------*/

        /*--------------------------闭幕雷击结束---------------------------*/


        BattleSession session = new BattleSession();
        session.setEnemy_deckport_id(underSeaDeckPort.getId());
        session.setMvp(getMVP(context));
        session.setShip_id(result.getApi_ship_ke());
        session.setWin_rank(getWinRank(context, memberShips, underSeaShips).name());

        updateAfterBattle(battleState, session);

        return result;
    }

    @Transactional
    public BattleResult battleresult(String member_id) {
        MemberMapBattleState state = mapBattleService.selectMemberMapBattleState(member_id);
        checkState(state.isBattleFlag());
        checkState(!state.isResultFlag());

        int mapInfoId = state.getTravellerNo();

        MapInfoModel mapInfo = mapService.getMapInfoById(mapInfoId);

        MemberDeckPort memberDeckPort = state.getMemberDeckPort();
        List<MemberShip> memberShips = memberDeckPort.getShips();

        BattleSession session = readJson(state.getSession(), BattleSession.class);

        int enemyDeckPortId = session.getEnemy_deckport_id();
        UnderSeaDeckPort underSeaDeckPort = underSeaDeckPortService.getUnderSeaDeckPortById(enemyDeckPortId);

        BattleResult result = new BattleResult();
        result.setShip_id(session.getShip_id());
        result.setWinRank(session.getWin_rank());

        WIN_RANK win_rank = WIN_RANK.valueOf(session.getWin_rank());
        int baseExp = DoubleMath.roundToInt(underSeaDeckPort.getExp() * BattleResult.EXP_AUG.get(win_rank.ordinal()), RoundingMode.CEILING);
        result.setGet_exp(underSeaDeckPort.getMemberExp());
        result.setMvp(session.getMvp());

        Member member = memberService.getMember(member_id);
        memberService.increaseMemberExp(member, baseExp);
        result.setMember_lv(member.getLevel());

        result.setMember_exp(member.getExperience());
        result.setBase_exp(underSeaDeckPort.getExp());
        int[] ship_exps = result.getShip_exp();

        for (ListIterator<MemberShip> iterator = memberShips.listIterator(); iterator.hasNext(); ) {
            int idx = iterator.nextIndex() + 1;
            MemberShip memberShip = iterator.next();

            if (idx == 1) {
                ship_exps[1] += baseExp;
            }
            ship_exps[idx] = idx == session.getMvp() ? 2 * baseExp : baseExp;

            int nowLv = memberShip.getLv();
            long[] now_exp = memberShip.getExp();
            long[] exp = ArrayUtils.subarray(now_exp, 0, 1);
            memberShipService.increaseMemberShipExp(memberShip, ship_exps[idx]);
            memberShipService.consumeFuelAndBullBaseMax(memberShip, 0.2f, 0.2f);
            int afterLv = memberShip.getLv();

            for (int lv = nowLv + 1; lv <= afterLv + 1; lv++) {
                exp = ArrayUtils.add(exp, shipService.getSumExpByLevel(lv));
            }
            result.addExp_lvup(exp);
        }

        result.setQuest_name(mapInfo.getApi_name());
        result.setQuest_level(mapInfo.getApi_level());
        result.setEnemy_info(underSeaDeckPort);
        result.setFirst_clear(0);
        result.setMapcell_incentive(0);

        int getFlag = GET_NONE;
        result.generateGetFlag(getFlag);

        if ((getFlag & GET_USEITEM) > 0) {
            result.setGet_useitem(null);
        }
        if ((getFlag & GET_SHIP) > 0) {
            result.setGet_ship(null);
        }
        if ((getFlag & GET_SLOTITEM) > 0) {
            result.setGet_slotitem(null);
        }

        updateAfterResult(state);
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    private void updateAfterBattle(MemberMapBattleState state, BattleSession session) {
        state.setBattleFlag(true);
        state.setResultFlag(false);
        state.setSession(writeJson(session, EMPTY_OBJECT_JSON));
        mapBattleService.updateMemberMapBattleStatus(state, BATTLE_FLAG, RESULT_FLAG, SESSION);
    }

    public void updateAfterBattleResult(String member_id) {
        MemberMapBattleState battleState = mapBattleService.selectMemberMapBattleState(member_id);
        updateAfterResult(battleState);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    private void updateAfterResult(MemberMapBattleState state) {
        state.setResultFlag(true);
        state.setBattleFlag(false);
        mapBattleService.updateMemberMapBattleStatus(state, BATTLE_FLAG, RESULT_FLAG);
    }

    private void firstShellingRound(final BattleContext context) {
        prepareHougkiContext(context);

        // 玩家攻击队列
        List<MemberShip> memberAttackShips = getAttackShips(context.getAliveMemberNormalShips());
        // 敌人攻击队列
        List<UnderSeaShip> underSeaAttackShips = getAttackShips(context.getAliveUnderSeaNormalShips());

        context.setMemberAttackShips(Lists.newLinkedList(FIRST_SHELL_SHIP_ORDER.sortedCopy(memberAttackShips)));
        context.setUnderSeaAttackShips(Lists.newLinkedList(FIRST_SHELL_SHIP_ORDER.sortedCopy(underSeaAttackShips)));

        startShellingRound(context);
    }

    private void secondShellingRound(final BattleContext context) {
        prepareHougkiContext(context);

        // 玩家攻击队列
        List<MemberShip> memberAttackShips = getAttackShips(context.getAliveMemberNormalShips());
        // 敌人攻击队列
        List<UnderSeaShip> underSeaAttackShips = getAttackShips(context.getAliveUnderSeaNormalShips());

        context.setMemberAttackShips(Lists.newLinkedList(memberAttackShips));
        context.setUnderSeaAttackShips(Lists.newLinkedList(underSeaAttackShips));

        startShellingRound(context);
    }

    private void prepareHougkiContext(final BattleContext context) {
        HougekiResult hougekiResult = new HougekiResult();
        context.getBattleResult().addHougekiResult(hougekiResult);
        context.setNowHougekiResult(hougekiResult);
    }

    private void startShellingRound(final BattleContext context) {
        LinkedList<MemberShip> memberAttackShips = context.getMemberAttackShips();
        LinkedList<UnderSeaShip> underSeaAttackShips = context.getUnderSeaAttackShips();

        int round = Math.max(memberAttackShips.size(), underSeaAttackShips.size());

        for (int i = 0; i < round; i++) {
            MemberShip attackShip = memberAttackShips.poll();
            if (ShipFilter.isAlive.test(attackShip)) {
                memberShipShellingSystem.generateHougkeResult(attackShip, context);
            }
            UnderSeaShip enemyAttackShip = underSeaAttackShips.poll();
            if (ShipFilter.isAlive.test(enemyAttackShip)) {
                underSeaShipShellingSystem.generateHougkeResult(enemyAttackShip, context);
            }
        }
    }

    private double getMemberLose(BattleContext context) {
        BattleSimulationResult result = context.getBattleResult();
        int[] maxHps = result.getApi_maxhps();
        int[] nowHps = result.getApi_nowhps();

        double maxHpSum = Arrays.stream(maxHps).skip(1L).limit(6L).sum();
        double nowHpSum = Arrays.stream(nowHps).skip(1L).limit(6L).sum();

        return nowHpSum / maxHpSum;
    }

    private double getEnemyLose(BattleContext context) {
        BattleSimulationResult result = context.getBattleResult();
        int[] maxHps = result.getApi_maxhps();
        int[] nowHps = result.getApi_nowhps();

        double maxHpSum = Arrays.stream(maxHps).skip(7L).limit(6L).sum();
        double nowHpSum = Arrays.stream(nowHps).skip(7L).limit(6L).sum();

        return nowHpSum / maxHpSum;
    }

    private double getEnemyDeckPortLostRatio(List<UnderSeaShip> underSeaShips) {
        int shipCount = underSeaShips.size();
        double loseCount = underSeaShips.stream().filter(ShipFilter.isAlive).count();
        return loseCount / shipCount;
    }

    private boolean isMemberShipLost(List<MemberShip> memberShips) {
        return memberShips.stream().anyMatch(ShipFilter.isAlive.negate());
    }

    /**
     * @param context       battle context
     * @param underSeaShips enemy ship list
     * @return win rank
     */
    private WIN_RANK getWinRank(BattleContext context, List<MemberShip> memberShips, List<UnderSeaShip> underSeaShips) {
        double memberLose = getMemberLose(context);
        double enemyLose = getEnemyLose(context);
        double enemyLostRatio = getEnemyDeckPortLostRatio(underSeaShips);
        boolean lost = isMemberShipLost(memberShips);

        if (enemyLose == 1d && !lost) {
            return memberLose == 0d ? WIN_RANK.SS : WIN_RANK.S;
        } else if (enemyLostRatio >= 2d / 3d && !lost) {
            return WIN_RANK.A;
        } else if (enemyLose >= 2 * memberLose) {
            return WIN_RANK.B;
        } else if (enemyLose >= memberLose) {
            return WIN_RANK.C;
        } else {
            return lost ? WIN_RANK.E : WIN_RANK.D;
        }
    }

    private int getMVP(BattleContext context) {

        Map<MemberShip, Integer> contextDamageSum = context.getDamageSum();

        Ordering<MemberShip> ordering = Ordering.natural().onResultOf(s -> contextDamageSum.getOrDefault(s, 0));

        List<MemberShip> memberShips = context.getMemberDeckPort().getShips();
        MemberShip mvpShip = ordering.max(memberShips);

        return context.getShipMap().inverse().get(mvpShip);
    }
}


