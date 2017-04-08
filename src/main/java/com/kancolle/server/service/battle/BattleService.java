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
import com.kancolle.server.model.mongo.MemberBattleFleet;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.BattleSession;
import com.kancolle.server.model.po.battle.MemberMapBattleState;
import com.kancolle.server.model.po.common.MaxMinValue;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.SlimDeckPort;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import com.kancolle.server.model.po.member.Member;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.UnderSeaShip;
import com.kancolle.server.service.base.BaseService;
import com.kancolle.server.service.battle.aerial.IAerialBattleSystem;
import com.kancolle.server.service.battle.course.ICourseSystem;
import com.kancolle.server.service.battle.map.MapBattleService;
import com.kancolle.server.service.battle.reconnaissance.ReconnaissanceAircraftSystem;
import com.kancolle.server.service.battle.shelling.template.ShellingTemplate;
import com.kancolle.server.service.deckport.MemberDeckPortService;
import com.kancolle.server.service.deckport.UnderSeaDeckPortService;
import com.kancolle.server.service.map.impl.MapService;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import com.kancolle.server.service.member.MemberService;
import com.kancolle.server.service.ship.MemberShipService;
import com.kancolle.server.service.ship.ShipService;
import com.kancolle.server.utils.logic.ship.ShipFilter;
import org.apache.commons.lang3.ArrayUtils;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.kancolle.server.model.kcsapi.battle.BattleResult.*;
import static com.kancolle.server.model.kcsapi.battle.BattleSimulationResult.FIRST_START_INDEX;
import static com.kancolle.server.model.kcsapi.battle.BattleSimulationResult.SECOND_START_INDEX;
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
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BattleService.class);
    @Autowired
    private MapBattleService mapBattleService;
    @Autowired
    private ReconnaissanceAircraftSystem reconnaissanceAircraftSystem;
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
    private MemberDeckPortService memberDeckPortService;
    @Autowired
    private UnderSeaDeckPortService underSeaDeckPortService;
    @Autowired
    private ShipService shipService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberShipService memberShipService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${api.battle.url}")
    private String battle_api_url;
    @Autowired
    private Map<String, AbstractMapCell> mapcells;
    @Autowired
    private Datastore datastore;

    public BattleSimulationResult battle(long member_id, BattleForm form) {
        MemberMapBattleState battleState = mapBattleService.selectMemberMapBattleState(member_id);
        //checkState(!battleState.isBattleFlag());

        int formation = form.getApi_formation();
        /* 旗艦大破進撃フラグ 0=通常, 1=応急修理要員を利用して進撃?, 2=応急修理女神を利用して進撃? */
        int recovery_type = form.getApi_recovery_type();

        // TODO 消耗損管

        // create battle context
        BattleContext context = new BattleContext();

        MemberDeckPort memberDeckPort = checkNotNull(battleState.getMemberDeckPort());
        context.setMemberDeckPort(memberDeckPort);

        int mapCellId = battleState.getMapCellId();
        AbstractMapCell mapCell = mapcells.get(String.format("mapCell%d", mapCellId));

        UnderSeaDeckPort underSeaDeckPort = mapCell.getUnderSeaDeckPort();
        context.setUnderSeaDeckPort(underSeaDeckPort);

        List<MemberShip> memberShips = memberDeckPort.getShips();
        List<UnderSeaShip> underSeaShips = underSeaDeckPortService.getUnderSeaDeckPortShips(underSeaDeckPort);
        context.setMemberShips(memberShips);
        context.setUnderSeaShips(underSeaShips);

        // build ship idx map
        //Map<Integer, IShip> memberShipMap = memberShips.stream().collect(Collectors.toMap(s -> 1 + memberShips.indexOf(s), s -> s));
        Map<Integer, IShip> memberShipMap = IntStream.range(0, memberShips.size()).boxed().collect(Collectors.toMap(i -> i + FIRST_START_INDEX, memberShips::get));
        Map<Integer, IShip> underSeaShipMap = IntStream.range(0, underSeaShips.size()).boxed().collect(Collectors.toMap(i -> i + SECOND_START_INDEX, underSeaShips::get));
        ImmutableBiMap<Integer, IShip> shipMap = new ImmutableBiMap.Builder<Integer, IShip>().putAll(memberShipMap).putAll(underSeaShipMap).build();
        context.setShipMap(shipMap);

        BattleSimulationResult result = new BattleSimulationResult(memberDeckPort, underSeaShips);
        context.setBattleResult(result);

        // 判断航向
        int course = courseSystem.generateCourse();
        result.setApi_formation(new int[]{formation, underSeaDeckPort.getFormation(), course});

        // 制空权
        int memberAerialPower = aerialBattleSystem.getMemberDeckPortAerialPower(memberShips);
        int underSeaAerialPower = aerialBattleSystem.getMemberDeckPortAerialPower(underSeaShips);
        int memberAerialState = aerialBattleSystem.getAerialPowerStatue(memberAerialPower, underSeaAerialPower);
        int underSeaAerialState = aerialBattleSystem.getAerialPowerStatue(underSeaAerialPower, memberAerialPower);
        context.setMemberAerialState(memberAerialState);
        context.setUnderSeaAerialState(underSeaAerialState);

        /*------------------------1.索敌开始------------------------*/

        /** 我方索敌 */
        int fsResult = reconnaissanceAircraftSystem.memberDeckPortSearchEnemy(memberShips, underSeaShips, underSeaAerialState);
        /** 敌方索敌 */
        int esResult = reconnaissanceAircraftSystem.enemyDeckPortSearchMember(memberShips, underSeaShips);

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

        saveMemberBattleFleet(member_id, memberDeckPort.getDeckId());

        try {
            result = restTemplate.getForObject(
                    battle_api_url + "/?api_member_id={member_id}&api_formation={formation}&api_recover_type={recover_type}",
                    BattleSimulationResult.class,
                    member_id,
                    form.getApi_formation(),
                    form.getApi_recovery_type());
        } catch (RuntimeException e) {
            LOGGER.error("error when apply kancolle-battle-api", e);
        }

        // 消耗損管

        // 擊沉

        return result;
    }

    private void saveMemberBattleFleet(long member_id, int... deck_ids) {
        List<SlimDeckPort> memberDeckPorts =
                Arrays.stream(deck_ids)
                        .mapToObj(deck_id -> memberDeckPortService.getEagerUnNullableMemberDeckPort(member_id, deck_id))
                        .collect(Collectors.toList());

        datastore.updateFirst(datastore.createQuery(MemberBattleFleet.class)
                        .field("member_id").equal(member_id).project("_id", true),
                datastore.createUpdateOperations(MemberBattleFleet.class).set("fleets", memberDeckPorts));
    }

    @Transactional
    public BattleResult battleresult(long member_id) {
        MemberMapBattleState state = mapBattleService.selectMemberMapBattleState(member_id);
        checkState(state.isBattleFlag());
        checkState(!state.isResultFlag());

        int mapInfoId = state.getTravellerNo();

        MapInfoModel mapInfo = mapService.getMapInfoById(mapInfoId);

        MemberDeckPort memberDeckPort = state.getMemberDeckPort();
        List<MemberShip> memberShips = memberDeckPort.getShips();

        BattleSession session = readJson(state.getSession(), BattleSession.class);
        final int mvp_idx = session.getMvp();

        int enemyDeckPortId = session.getEnemy_deckport_id();
        UnderSeaDeckPort underSeaDeckPort = underSeaDeckPortService.getUnderSeaDeckPortById(enemyDeckPortId);

        BattleResult result = new BattleResult();

        result.setShip_id(session.getShip_id());

        final WinRank winRank = WinRank.valueOf(session.getWin_rank());
        result.setWinRank(winRank.showName);

        int baseExp = DoubleMath.roundToInt(underSeaDeckPort.getExp() * BattleResult.EXP_AUG.get(winRank.ordinal()), RoundingMode.CEILING);
        result.setGet_exp(underSeaDeckPort.getMemberExp());
        result.setMvp(mvp_idx);

        Member member = memberService.getMember(member_id);
        memberService.increaseMemberExp(member, baseExp);
        result.setMember_lv(member.getLevel());

        result.setMember_exp(member.getExperience());
        result.setBase_exp(underSeaDeckPort.getExp());
        int[] ship_exps = result.getShip_exp();

        // exp
        for (ListIterator<MemberShip> iterator = memberShips.listIterator(); iterator.hasNext(); ) {
            int idx = iterator.nextIndex() + 1;
            MemberShip memberShip = iterator.next();

            if (idx == 1) {
                ship_exps[1] += baseExp;
            }
            ship_exps[idx] = idx == mvp_idx ? 2 * baseExp : baseExp;

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

        processCondAfterBattleResult(memberShips, mvp_idx, winRank);

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

    public void updateAfterBattleResult(long member_id) {
        MemberMapBattleState battleState = mapBattleService.selectMemberMapBattleState(member_id);
        if (battleState != null) {
            updateAfterResult(battleState);
        }
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
                memberShipShellingSystem.generateHougekiResult(attackShip, context);
            }
            UnderSeaShip enemyAttackShip = underSeaAttackShips.poll();
            if (ShipFilter.isAlive.test(enemyAttackShip)) {
                underSeaShipShellingSystem.generateHougekiResult(enemyAttackShip, context);
            }
        }
    }

    private double getLose(final int[] nowHps, final List<? extends IShip> ships) {
        double nowHpSum = Arrays.stream(nowHps).limit(ships.size()).sum();

        double afterBattleHpSum = ships.stream().mapToInt(IShip::getNowHp).sum();

        return 1d - afterBattleHpSum / nowHpSum;
    }

    private double getEnemyDeckPortLostRatio(List<UnderSeaShip> underSeaShips) {
        int shipCount = underSeaShips.size();
        double loseCount = underSeaShips.stream().filter(ShipFilter.isAlive.negate()).count();
        return loseCount / shipCount;
    }

    private boolean isMemberShipLost(List<? extends IShip> memberShips) {
        return memberShips.stream().anyMatch(ShipFilter.isAlive.negate());
    }

    /**
     * @param context       battle context
     * @param underSeaShips enemy ship list
     * @return win rank
     */
    private WinRank getWinRank(BattleContext context, List<MemberShip> memberShips, List<UnderSeaShip> underSeaShips) {
        final int[] nowHps = context.getBattleResult().getApi_nowhps();
        final int[] memberNowHps = Arrays.copyOfRange(nowHps, FIRST_START_INDEX, SECOND_START_INDEX);
        final int[] underSeaNowHps = Arrays.copyOfRange(nowHps, SECOND_START_INDEX, nowHps.length + 1);

        double memberLose = getLose(memberNowHps, context.getMemberShips());
        double enemyLose = getLose(underSeaNowHps, context.getUnderSeaShips());
        double enemyLostRatio = getEnemyDeckPortLostRatio(underSeaShips);
        boolean lost = isMemberShipLost(memberShips);

        if (enemyLose == 1d && !lost) {
            return memberLose == 0d ? WinRank.SS : WinRank.S;
        } else if (enemyLostRatio >= 2d / 3d && !lost) {
            return WinRank.A;
        } else if (enemyLose > 2 * memberLose) {
            return WinRank.B;
        } else if (enemyLose > memberLose) {
            return WinRank.C;
        } else {
            return lost ? WinRank.E : WinRank.D;
        }
    }

    private int getMVP(BattleContext context) {

        Map<MemberShip, Integer> contextDamageSum = context.getDamageSum();

        Ordering<MemberShip> ordering = Ordering.natural().onResultOf(s -> contextDamageSum.getOrDefault(s, 0));

        List<MemberShip> memberShips = context.getMemberDeckPort().getShips();
        MemberShip mvpShip = ordering.max(memberShips);

        return context.getShipMap().inverse().get(mvpShip);
    }

    /**
     * 根据战斗评价和mvp计算状态值增益
     *
     * @param ships
     * @param mvp_idx
     * @param winRank
     */
    private void processCondAfterBattleResult(final List<MemberShip> ships, final int mvp_idx, final WinRank winRank) {
        final int[] increase_conds = new int[ships.size()];
        Arrays.fill(increase_conds, BattleResult.COND_AUG.get(winRank.ordinal()));
        increase_conds[0] += 3;
        increase_conds[mvp_idx - 1] += 10;

        IntStream.range(0, ships.size())
                .forEach(i -> {
                    int increaseCond = increase_conds[i];
                    MemberShip ship = ships.get(i);
                    memberShipService.updateMemberShipCond(ship, increaseCond);
                });
    }
}


