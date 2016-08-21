package com.kancolle.server.model.po.battle;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.kancolle.server.model.kcsapi.battle.BattleSimulationResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.UnderSeaShip;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BattleContext {

    private BattleSimulationResult battleResult;

    private MemberDeckPort memberDeckPort;

    private UnderSeaDeckPort underSeaDeckPort;

    private List<MemberShip> aliveMemberSSShips;

    private List<MemberShip> aliveMemberNormalShips;

    private List<UnderSeaShip> aliveUnderSeaSSShips;

    private List<UnderSeaShip> aliveUnderSeaNormalShips;

    private int currentAerialState;

    private int memberAerialState;

    private int underSeaAerialState;

    private LinkedList<MemberShip> memberAttackShips;

    private LinkedList<UnderSeaShip> underSeaAttackShips;

    private ImmutableBiMap<Integer, IShip> shipMap;

    private ImmutableMap<IShip, SlotItemInfo> shipSlotItemInfoMap;

    private HougekiResult nowHougekiResult;

    private List<? extends IShip> enemySSShips;

    private List<? extends IShip> enemyNormalShips;

    private final Map<MemberShip, Integer> damageSum = Maps.newHashMap();

    public BattleContext() {
    }

    public BattleSimulationResult getBattleResult() {
        return battleResult;
    }

    public void setBattleResult(BattleSimulationResult battleResult) {
        this.battleResult = battleResult;
    }

    public MemberDeckPort getMemberDeckPort() {
        return memberDeckPort;
    }

    public void setMemberDeckPort(MemberDeckPort memberDeckPort) {
        this.memberDeckPort = memberDeckPort;
    }

    public UnderSeaDeckPort getUnderSeaDeckPort() {
        return underSeaDeckPort;
    }

    public void setUnderSeaDeckPort(UnderSeaDeckPort underSeaDeckPort) {
        this.underSeaDeckPort = underSeaDeckPort;
    }

    public List<MemberShip> getAliveMemberSSShips() {
        return aliveMemberSSShips;
    }

    public void setAliveMemberSSShips(List<MemberShip> aliveMemberSSShips) {
        this.aliveMemberSSShips = aliveMemberSSShips;
    }

    public List<MemberShip> getAliveMemberNormalShips() {
        return aliveMemberNormalShips;
    }

    public void setAliveMemberNormalShips(List<MemberShip> aliveMemberNormalShips) {
        this.aliveMemberNormalShips = aliveMemberNormalShips;
    }

    public List<UnderSeaShip> getAliveUnderSeaSSShips() {
        return aliveUnderSeaSSShips;
    }

    public void setAliveUnderSeaSSShips(List<UnderSeaShip> aliveUnderSeaSSShips) {
        this.aliveUnderSeaSSShips = aliveUnderSeaSSShips;
    }

    public List<UnderSeaShip> getAliveUnderSeaNormalShips() {
        return aliveUnderSeaNormalShips;
    }

    public void setAliveUnderSeaNormalShips(List<UnderSeaShip> aliveUnderSeaNormalShips) {
        this.aliveUnderSeaNormalShips = aliveUnderSeaNormalShips;
    }

    public int getCurrentAerialState() {
        return currentAerialState;
    }

    public void setCurrentAerialState(int currentAerialState) {
        this.currentAerialState = currentAerialState;
    }

    public int getMemberAerialState() {
        return memberAerialState;
    }

    public void setMemberAerialState(int memberAerialState) {
        this.memberAerialState = memberAerialState;
    }

    public int getUnderSeaAerialState() {
        return underSeaAerialState;
    }

    public void setUnderSeaAerialState(int underSeaAerialState) {
        this.underSeaAerialState = underSeaAerialState;
    }

    public LinkedList<MemberShip> getMemberAttackShips() {
        return memberAttackShips;
    }

    public void setMemberAttackShips(LinkedList<MemberShip> memberAttackShips) {
        this.memberAttackShips = memberAttackShips;
    }

    public LinkedList<UnderSeaShip> getUnderSeaAttackShips() {
        return underSeaAttackShips;
    }

    public void setUnderSeaAttackShips(LinkedList<UnderSeaShip> underSeaAttackShips) {
        this.underSeaAttackShips = underSeaAttackShips;
    }

    public ImmutableBiMap<Integer, IShip> getShipMap() {
        return shipMap;
    }

    public void setShipMap(ImmutableBiMap<Integer, IShip> shipMap) {
        this.shipMap = shipMap;
    }

    public ImmutableMap<IShip, SlotItemInfo> getShipSlotItemInfoMap() {
        return shipSlotItemInfoMap;
    }

    public void setShipSlotItemInfoMap(ImmutableMap<IShip, SlotItemInfo> shipSlotItemInfoMap) {
        this.shipSlotItemInfoMap = shipSlotItemInfoMap;
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
