package com.kancolle.server.model.po.battle;

import com.google.common.collect.ImmutableBiMap;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;

import java.util.LinkedList;
import java.util.List;

public class BattleContext {

    private BattleSimulationResult battleResult;

    private List<MemberShip> memberSSShips;

    private List<MemberShip> memberOtherShips;

    private List<EnemyShip> enemySSShips;

    private List<EnemyShip> enemyOtherShips;

    private LinkedList<MemberShip> memberAttackShips;

    private LinkedList<EnemyShip> enemyAttackShips;

    private ImmutableBiMap<Integer, IShip> shipMap;

    private HougekiResult nowHougekiResult;

    private List<IShip> nowSSShips;

    private List<IShip> nowOtherShips;

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

    public List<MemberShip> getMemberOtherShips() {
        return memberOtherShips;
    }

    public void setMemberOtherShips(List<MemberShip> memberOtherShips) {
        this.memberOtherShips = memberOtherShips;
    }

    public List<EnemyShip> getEnemySSShips() {
        return enemySSShips;
    }

    public void setEnemySSShips(List<EnemyShip> enemySSShips) {
        this.enemySSShips = enemySSShips;
    }

    public List<EnemyShip> getEnemyOtherShips() {
        return enemyOtherShips;
    }

    public void setEnemyOtherShips(List<EnemyShip> enemyOtherShips) {
        this.enemyOtherShips = enemyOtherShips;
    }

    public LinkedList<MemberShip> getMemberAttackShips() {
        return memberAttackShips;
    }

    public void setMemberAttackShips(LinkedList<MemberShip> memberAttackShips) {
        this.memberAttackShips = memberAttackShips;
    }

    public LinkedList<EnemyShip> getEnemyAttackShips() {
        return enemyAttackShips;
    }

    public void setEnemyAttackShips(LinkedList<EnemyShip> enemyAttackShips) {
        this.enemyAttackShips = enemyAttackShips;
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

    public List<IShip> getNowSSShips() {
        return nowSSShips;
    }

    public void setNowSSShips(List<IShip> nowSSShips) {
        this.nowSSShips = nowSSShips;
    }

    public List<IShip> getNowOtherShips() {
        return nowOtherShips;
    }

    public void setNowOtherShips(List<IShip> nowOtherShips) {
        this.nowOtherShips = nowOtherShips;
    }
}
