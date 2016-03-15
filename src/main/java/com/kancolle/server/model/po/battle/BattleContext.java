package com.kancolle.server.model.po.battle;

import com.google.common.collect.ImmutableBiMap;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.ship.AbstractShip;
import com.kancolle.server.model.po.ship.EnemyShip;
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

    private ImmutableBiMap<Integer, AbstractShip> shipMap;

    private HougekiResult nowHougekiResult;

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

    public ImmutableBiMap<Integer, AbstractShip> getShipMap() {
        return shipMap;
    }

    public void setShipMap(ImmutableBiMap<Integer, AbstractShip> shipMap) {
        this.shipMap = shipMap;
    }

    public HougekiResult getNowHougekiResult() {
        return nowHougekiResult;
    }

    public void setNowHougekiResult(HougekiResult nowHougekiResult) {
        this.nowHougekiResult = nowHougekiResult;
    }
}
