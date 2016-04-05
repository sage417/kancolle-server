/**
 *
 */
package com.kancolle.server.service.battle;

import com.google.common.collect.ImmutableBiMap;
import com.kancolle.server.controller.kcsapi.battle.form.BattleForm;
import com.kancolle.server.mapper.map.MemberMapBattleMapper;
import com.kancolle.server.model.kcsapi.battle.BattleResult;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.kcsapi.start.sub.MapInfoModel;
import com.kancolle.server.model.po.battle.BattleContext;
import com.kancolle.server.model.po.battle.MemberMapBattleState;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.battle.aerial.IAerialBattleSystem;
import com.kancolle.server.service.battle.course.ICourseSystem;
import com.kancolle.server.service.battle.reconnaissance.IReconnaissanceAircraftSystem;
import com.kancolle.server.service.battle.shelling.IShellingSystem;
import com.kancolle.server.service.map.impl.MapService;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import com.kancolle.server.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.kancolle.server.utils.logic.DeckPortUtils.getAttackShips;
import static com.kancolle.server.utils.logic.ship.ShipFilter.*;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 */
@Service
public class BattleService implements IBattleService {

    @Autowired
    private MemberMapBattleMapper memberMapBattleMapper;

    @Autowired
    private IReconnaissanceAircraftSystem reconnaissanceAircraftSystem;

    @Autowired
    private ICourseSystem courseSystem;

    @Autowired
    private IAerialBattleSystem aerialBattleSystem;

    @Autowired
    private IShellingSystem<MemberShip, EnemyShip> memberShipShellingSystem;

    @Autowired
    private IShellingSystem<EnemyShip, MemberShip> enemyShipShellingSystem;

    @Autowired
    private MapService mapService;

    @Override
    public BattleSimulationResult battle(String member_id, BattleForm form) {
        MemberMapBattleState battleState = memberMapBattleMapper.selectMemberMapBattleState(member_id);
        checkState(!battleState.isBattleFlag());

        int formation = form.getApi_formation();
        /* 旗艦大破進撃フラグ 0=通常, 1=応急修理要員を利用して進撃?, 2=応急修理女神を利用して進撃? */
        int recovery_type = form.getApi_recovery_type();

        // create battle context
        BattleContext context = new BattleContext();

        MemberDeckPort memberDeckPort = checkNotNull(battleState.getMemberDeckPort());

        int mapCellId = battleState.getMapCellId();
        AbstractMapCell mapCell = SpringUtils.getBean(String.format("mapCell%d", mapCellId), AbstractMapCell.class);

        EnemyDeckPort enemyDeckPort = mapCell.getEnemyDeckPort();
        BattleSimulationResult result = new BattleSimulationResult(memberDeckPort, enemyDeckPort);

        // 判断航向
        int course = courseSystem.generateCourse();
        result.setApi_formation(new int[]{formation, enemyDeckPort.getFormation(), course});

        // 制空权
        int memberAerialPower = aerialBattleSystem.getMemberDeckPortAerialPower(memberDeckPort.getShips());
        int eneryAerialPower = aerialBattleSystem.getMemberDeckPortAerialPower(enemyDeckPort.getEnemyShips());
        int aerialState = aerialBattleSystem.getAerialPowerStatue(memberAerialPower, eneryAerialPower);

        /*------------------------1.索敌开始------------------------*/

        /** 我方索敌 */
        int fsResult = reconnaissanceAircraftSystem.memberDeckPortSearchEnemy(memberDeckPort, enemyDeckPort, aerialState);
        /** 敌方索敌 */
        int esResult = reconnaissanceAircraftSystem.enemyDeckPortSearchMember(memberDeckPort, enemyDeckPort);

        result.setApi_search(new int[]{fsResult, esResult});

        boolean memberSearchSuccess = reconnaissanceAircraftSystem.isSearchSuccess(fsResult);
        boolean enemySearchSuccess = reconnaissanceAircraftSystem.isSearchSuccess(esResult);
        /*-------------------------索敌结束------------------------*/

        /*------------------------2.航空战开始开始------------------------*/

        /*-------制空战开始-------*/

        /*-------制空战结束-------*/

        /*------防空火炮迎击开始------*/

        /*------防空火炮迎击结束------*/

        /*-------航空接触开始-------*/

        /*-------航空接触结束-------*/

        /*-------攻击机、轰炸机的雷击、轰炸-----*/

        /*-------攻击机、轰炸机的雷击、轰炸结束-----*/
        /*-------------------------航空战开始结束-----------------------*/

        /*-------------------------3支援舰队达到------------------------*/

        /*--------------------------支援舰队返航------------------------*/

        /*--------------------------4.开幕雷击---------------------------*/

        /*--------------------------开幕雷击结束---------------------------*/

        /*--------------------------5.炮击战---------------------------*/
        List<MemberShip> memberShips = memberDeckPort.getShips();
        List<EnemyShip> enemyShips = enemyDeckPort.getEnemyShips();

        boolean hasBB = memberShips.stream().anyMatch(BBShipFilter) || enemyShips.stream().anyMatch(BBShipFilter);
        int[] api_hourai_flag = {1, hasBB ? 1 : 0, 0, 0};
        result.setApi_hourai_flag(api_hourai_flag);

        // 玩家潜艇队列，无法被攻击的潜艇将被移除
        List<MemberShip> memberSSShips = getTargetShips(memberShips, ssFilter);
        // 玩家非潜艇队列，无法被攻击的舰船将被移除
        List<MemberShip> memberOtherShips = getTargetShips(memberShips, ssFilter.negate());

        // 敌方潜艇队列，无法被攻击的潜艇将被移除
        List<EnemyShip> enemySSShips = getTargetShips(enemyShips, ssFilter);
        // 敌方非潜艇队列，无法被攻击的舰船将被移除
        List<EnemyShip> enemyOtherShips = getTargetShips(enemyShips, ssFilter.negate());

        Map<Integer, IShip> memberShipMap = memberShips.stream().collect(Collectors.toMap(s -> 1 + memberShips.indexOf(s), s -> s));
        Map<Integer, IShip> enmeyShipMap = IntStream.range(0, enemyShips.size()).boxed().collect(Collectors.toMap(i -> i + 7, enemyShips::get));
        ImmutableBiMap.Builder<Integer, IShip> abstractShipBuilder = ImmutableBiMap.builder();
        ImmutableBiMap<Integer, IShip> shipMap = abstractShipBuilder.putAll(memberShipMap).putAll(enmeyShipMap).build();

        // 玩家攻击队列
        LinkedList<MemberShip> memberAttackShips = getAttackShips(memberOtherShips, enemyOtherShips.isEmpty());
        // 敌人攻击队列
        LinkedList<EnemyShip> enemyAttackShips = getAttackShips(enemyOtherShips, memberOtherShips.isEmpty());

        context.setBattleResult(result);
        context.setMemberSSShips(memberSSShips);
        context.setMemberOtherShips(memberOtherShips);
        context.setMemberAttackShips(memberAttackShips);
        context.setEnemySSShips(enemySSShips);
        context.setEnemyOtherShips(enemyOtherShips);
        context.setEnemyAttackShips(enemyAttackShips);
        context.setShipMap(shipMap);


        int shellingRound = hasBB ? 2 : 1;

        for (int i = 0; i < shellingRound; i++) {
            HougekiResult hougekiResult = new HougekiResult();
            result.addHougekiResult(hougekiResult);
            context.setNowHougekiResult(hougekiResult);
            shellingRound(context);
        }

        /*--------------------------炮击战---------------------------*/

        /*--------------------------6.闭幕雷击---------------------------*/

        /*--------------------------闭幕雷击结束---------------------------*/
        battleState.setBattleFlag(true);
        memberMapBattleMapper.update(battleState, "battleFlag");
        return result;
    }

    @Override
    @Transactional
    public BattleResult battleresult(String member_id) {
        // TODO member exp
        // TODO member ships exp
        // TODO resource comsume
        MemberMapBattleState state = memberMapBattleMapper.selectMemberMapBattleState(member_id);
        checkState(state.isBattleFlag());
        checkState(!state.isResultFlag());

        int mapInfoId = state.getTravellerNo();

        MapInfoModel mapInfo = mapService.getMapInfoById(mapInfoId);

        BattleResult result = new BattleResult();
//        result.setShip_id();
        result.setWinRank(BattleResult.WIN_RANK.SS);
//        result.setGet_exp();
//        result.setMvp();
//        result.setMember_lv();
//        result.setMember_exp();
//        result.setBase_exp();
//        result.setShip_exp();
//        result.setExp_lvup();
        result.setQuest_name(mapInfo.getApi_name());
        result.setQuest_level(mapInfo.getApi_level());
//        result.setEnemy_info();
        result.setFirst_clear(0);
        result.setMapcell_incentive(0);

        int getFlag = BattleResult.GET_NONE;
        result.generateGetFlag(getFlag);

        if ((getFlag & BattleResult.GET_USEITEM) > 0) {
            result.setGet_useitem(null);
        }
        if ((getFlag & BattleResult.GET_SHIP) > 0) {
            result.setGet_ship(null);
        }
        if ((getFlag & BattleResult.GET_SLOTITEM) > 0) {
            result.setGet_slotitem(null);
        }

        state.setResultFlag(true);
        memberMapBattleMapper.update(state, "resultFlag");



        return result;
    }

    private void shellingRound(BattleContext context) {
        LinkedList<MemberShip> memberAttackShips = context.getMemberAttackShips();
        LinkedList<EnemyShip> enemyAttackShips = context.getEnemyAttackShips();
        int circulRounds = Math.max(memberAttackShips.size(), enemyAttackShips.size());
        for (int i = 0; i < circulRounds; i++) {
            MemberShip attackShip = memberAttackShips.poll();
            if (attackShip != null && attackShip.getNowHp() > 0) {
                context.switchToMemberContext();
                memberShipShellingSystem.generateHougkeResult(attackShip, context);
            }
            EnemyShip enemyAttackShip = enemyAttackShips.poll();
            if (enemyAttackShip != null && enemyAttackShip.getNowHp() > 0) {
                context.switchToEnemyContext();
                enemyShipShellingSystem.generateHougkeResult(enemyAttackShip, context);
            }
        }
    }
}


