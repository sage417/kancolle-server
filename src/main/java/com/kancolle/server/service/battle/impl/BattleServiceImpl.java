/**
 * 
 */
package com.kancolle.server.service.battle.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.kancolle.server.controller.kcsapi.battle.form.BattleForm;
import com.kancolle.server.mapper.map.MemberMapBattleMapper;
import com.kancolle.server.model.kcsapi.battle.BattleResult;
import com.kancolle.server.model.po.battle.MemberMapBattleState;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.service.battle.BattleService;
import com.kancolle.server.service.battle.ReconnaissanceAircraftSystem;
import com.kancolle.server.service.map.mapcells.AbstractMapCell;

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

    @Override
    public BattleResult battle(String member_id, BattleForm form) {
        int formation = form.getApi_formation();
        /*旗艦大破進撃フラグ　0=通常, 1=応急修理要員を利用して進撃?, 2=応急修理女神を利用して進撃?*/
        int recovery_type = form.getApi_formation();

        MemberMapBattleState battleState = memberMapBattleMapper.selectMemberMapBattleState(member_id);

        MemberDeckPort memberDeckPort = checkNotNull(battleState.getMemberDeckPort());

        int mapCellId = battleState.getMapCellId();
        AbstractMapCell mapCell = ContextLoader.getCurrentWebApplicationContext().getBean(String.format("mapCell%d", mapCellId), AbstractMapCell.class);

        EnemyDeckPort enemyDeckPort = mapCell.getEnemyDeckPort();
        BattleResult result = new BattleResult(memberDeckPort, enemyDeckPort);

        /*------------------------1.索敌开始------------------------*/

        /** 我方索敌 */
        int fsResult = reconnaissanceAircraftSystem.memberDeckPortSearchEnemy(memberDeckPort, enemyDeckPort);
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

        /*--------------------------炮击战---------------------------*/

        /*--------------------------6.闭幕雷击---------------------------*/

        /*--------------------------闭幕雷击结束---------------------------*/
        result.setApi_formation(new int[] { formation, enemyDeckPort.getFormation(), 1 });
        return result;
    }
}
