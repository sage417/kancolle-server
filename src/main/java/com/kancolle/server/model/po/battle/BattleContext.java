package com.kancolle.server.model.po.battle;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Maps;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.UnderSeaShip;

import java.util.List;
import java.util.Map;

public class BattleContext {

    private BattleSimulationResult battleResult;

    private List<MemberShip> memberSSShips;

    private List<MemberShip> memberNormalShips;

    private List<UnderSeaShip> underSeaSSShips;

    private List<UnderSeaShip> underSeaNormalShips;

    private List<MemberShip> memberAttackShips;

    private List<UnderSeaShip> underSeaAttackShips;

    private ImmutableBiMap<Integer, IShip> shipMap;

    private HougekiResult nowHougekiResult;

    private List<? extends IShip> enemySSShips;

    private List<? extends IShip> enemyNormalShips;

    private final Map<MemberShip, Integer> damageSum;

    public BattleContext() {
        this.damageSum = Maps.newHashMap();
    }

    public BattleSimulationResult getBattleResult() {
        return battleResult;
    }

    public void setBattleResult(BattleSimulationResult battleResult) {
        this.battleResult = battleResult;
    }

    public List<MemberShip> getMemberSSShips() {
        return memberSSShips;
    }

    public void setMemberSSShips(List<MemberShip> memberSSShips) {
        this.memberSSShips = memberSSShips;
    }

    public List<MemberShip> getMemberNormalShips() {
        return memberNormalShips;
    }

    public void setMemberNormalShips(List<MemberShip> memberNormalShips) {
        this.memberNormalShips = memberNormalShips;
    }

    public List<UnderSeaShip> getUnderSeaSSShips() {
        return underSeaSSShips;
    }

    public void setUnderSeaSSShips(List<UnderSeaShip> underSeaSSShips) {
        this.underSeaSSShips = underSeaSSShips;
    }

    public List<UnderSeaShip> getUnderSeaNormalShips() {
        return underSeaNormalShips;
    }

    public void setUnderSeaNormalShips(List<UnderSeaShip> underSeaNormalShips) {
        this.underSeaNormalShips = underSeaNormalShips;
    }

    public List<MemberShip> getMemberAttackShips() {
        return memberAttackShips;
    }

    public void setMemberAttackShips(List<MemberShip> memberAttackShips) {
        this.memberAttackShips = memberAttackShips;
    }

    public List<UnderSeaShip> getUnderSeaAttackShips() {
        return underSeaAttackShips;
    }

    public void setUnderSeaAttackShips(List<UnderSeaShip> underSeaAttackShips) {
        this.underSeaAttackShips = underSeaAttackShips;
    }

    public ImmutableBiMap<Integer, IShip> getShipMap() {
        return shipMap;
    }

    public void setShipMap(ImmutableBiMap<Integer, IShip> shipMap) {
        this.shipMap = shipMap;
    }

    public HougekiResult getNowHougekiResult() {
        return nowHougekiResult;
    }

    public void setNowHougekiResult(HougekiResult nowHougekiResult) {
        this.nowHougekiResult = nowHougekiResult;
    }

    public List<? extends IShip> getEnemySSShips() {
        return enemySSShips;
    }

    public void setEnemySSShips(List<? extends IShip> enemySSShips) {
        this.enemySSShips = enemySSShips;
    }

    public List<? extends IShip> getEnemyNormalShips() {
        return enemyNormalShips;
    }

    public void setEnemyNormalShips(List<? extends IShip> enemyNormalShips) {
        this.enemyNormalShips = enemyNormalShips;
    }

    public Map<MemberShip, Integer> getDamageSum() {
        return damageSum;
    }

}
