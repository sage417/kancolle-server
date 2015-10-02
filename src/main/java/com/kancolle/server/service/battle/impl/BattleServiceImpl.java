/**
 * 
 */
package com.kancolle.server.service.battle.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.kancolle.server.utils.logic.ship.ShipFilter.antiSSShipFilter;
import static com.kancolle.server.utils.logic.ship.ShipFilter.ssFilter;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.kancolle.server.controller.kcsapi.battle.form.BattleForm;
import com.kancolle.server.mapper.map.MemberMapBattleMapper;
import com.kancolle.server.model.kcsapi.battle.BattleResult;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.battle.MemberMapBattleState;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.service.battle.AerialBattleSystem;
import com.kancolle.server.service.battle.BattleService;
import com.kancolle.server.service.battle.CourseSystem;
import com.kancolle.server.service.battle.ReconnaissanceAircraftSystem;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;
import com.kancolle.server.utils.logic.DeckPortUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
@Service
public class BattleServiceImpl implements BattleService {

    @Autowired
    private MemberMapBattleMapper memberMapBattleMapper;

    @Autowired
    private ReconnaissanceAircraftSystem reconnaissanceAircraftSystem;

    @Autowired
    private CourseSystem courseSystem;

    @Autowired
    private AerialBattleSystem aerialBattleSystem;

    @Override
    public BattleSimulationResult battle(String member_id, BattleForm form) {
        int formation = form.getApi_formation();
        /* 旗艦大破進撃フラグ 0=通常, 1=応急修理要員を利用して進撃?, 2=応急修理女神を利用して進撃? */
        int recovery_type = form.getApi_formation();

        MemberMapBattleState battleState = memberMapBattleMapper.selectMemberMapBattleState(member_id);

        MemberDeckPort memberDeckPort = checkNotNull(battleState.getMemberDeckPort());

        int mapCellId = battleState.getMapCellId();
        AbstractMapCell mapCell = ContextLoader.getCurrentWebApplicationContext().getBean(String.format("mapCell%d", mapCellId), AbstractMapCell.class);

        EnemyDeckPort enemyDeckPort = mapCell.getEnemyDeckPort();
        BattleSimulationResult result = new BattleSimulationResult(memberDeckPort, enemyDeckPort);

        // 判断航向
        int course = courseSystem.calCourse();
        result.setApi_formation(new int[] { formation, enemyDeckPort.getFormation(), course });

        // 制空权
        int memberAerialPower = aerialBattleSystem.getMemberDeckPortAerialPower(memberDeckPort.getShips());
        int eneryAerialPower = aerialBattleSystem.getMemberDeckPortAerialPower(enemyDeckPort.getEnemyShips());
        int aerialState = aerialBattleSystem.getAerialPowerStatue(memberAerialPower, eneryAerialPower);

        /*------------------------1.索敌开始------------------------*/

        /** 我方索敌 */
        int fsResult = reconnaissanceAircraftSystem.memberDeckPortSearchEnemy(memberDeckPort, enemyDeckPort, aerialState);
        /** 敌方索敌 */
        int esResult = reconnaissanceAircraftSystem.enemyDeckPortSearchMember(memberDeckPort, enemyDeckPort);

        result.setApi_search(new int[] { fsResult, esResult });

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

        // 玩家潜艇队列
        List<MemberShip> memberSSShips = memberShips.stream().filter(ssFilter).collect(Collectors.toList());
        // 玩家非潜艇队列
        List<MemberShip> memberOtherShips = memberShips.stream().filter(ssFilter.negate()).collect(Collectors.toList());

        // 敌方潜艇队列
        List<EnemyShip> enemySSShips = enemyShips.stream().filter(ssFilter).collect(Collectors.toList());
        // 敌方非潜艇队列
        List<EnemyShip> enemyOtherShips = enemyShips.stream().filter(ssFilter.negate()).collect(Collectors.toList());

        // 玩家攻击队列
        List<MemberShip> memberAttackShips = DeckPortUtils.getAttackShips(memberOtherShips, enemyOtherShips.isEmpty());
        // 敌人攻击队列
        List<EnemyShip> enemyAttackShips = DeckPortUtils.getAttackShips(enemyOtherShips, memberOtherShips.isEmpty());

        HougekiResult hougekiResult1 = new HougekiResult();

        int[] enemyNowHp = enemyShips.stream().mapToInt(ship -> ship.getShip().getTaikArray()[1]).toArray();

        int circulRounds = Math.max(memberAttackShips.size(), enemyAttackShips.size());
        for (int i = 0; i < circulRounds; i++) {

            if (i < memberAttackShips.size() && memberAttackShips.get(i).getNowHp() > 0) {
                MemberShip memberShip = memberAttackShips.get(i);
                hougekiResult1.getApi_at_list().add(memberShip.getMemberShipId());
                int shipType = memberShip.getShip().getShipTypeId();
                if (!enemySSShips.isEmpty() && antiSSShipFilter.test(memberShip)) {
                    EnemyShip defEnemyShip = enemySSShips.get(RandomUtils.nextInt(0, enemySSShips.size()));
                    // 反潜攻击
                    if (shipType == 7 || shipType == 11) {

                    } else {

                    }
                } else {
                    // 炮击
                    hougekiResult1.getApi_at_type().add(0);
                    EnemyShip defEnemyShip = enemyOtherShips.get(RandomUtils.nextInt(0, enemyOtherShips.size()));
                    hougekiResult1.getApi_df_list().add(new int[] { defEnemyShip.getShip().getShipId(), defEnemyShip.getShip().getShipId() });
                    if (shipType == 7 || shipType == 11) {

                    } else {
                        hougekiResult1.getApi_si_list().add(new int[] { 1, 2 });
                        hougekiResult1.getApi_cl_list().add(new int[] { 1, 1 });
                        hougekiResult1.getApi_damage().add(new int[] { 100, 100 });
                    }
                }
            }

            if (i < enemyShips.size() && enemyNowHp[i] > 0) {
                EnemyShip enemyShip = enemyAttackShips.get(i);
                int shipType = enemyShip.getShip().getShipTypeId();
                if (!memberSSShips.isEmpty() && antiSSShipFilter.test(enemyShip)) {
                    // 反潜攻击
                    if (shipType == 7 || shipType == 11) {

                    } else {

                    }
                }
                // 炮击
                if (shipType == 7 || shipType == 11) {

                } else {

                }
            }
        }
        result.setApi_hougeki1(hougekiResult1);

        /*--------------------------炮击战---------------------------*/

        /*--------------------------6.闭幕雷击---------------------------*/

        /*--------------------------闭幕雷击结束---------------------------*/
        return result;

    }

    @Override
    public BattleResult battleresult(String member_id) {
        return null;
    }
}
