/**
 *
 */
package com.kancolle.server.model.kcsapi.battle;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.kancolle.server.model.kcsapi.battle.houku.KouKuResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.deckport.EnemyDeckPort;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.EnemyShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
public class BattleSimulationResult {

    private static final int MAX_SHELLING_ROUND = 3;

    @JSONField(ordinal = 1)
    private int api_dock_id;

    /** 敌舰队舰船ID，-1开始，-1结束，空舰船ID为-1 */
    @JSONField(ordinal = 2)
    private int[] api_ship_ke;

    @JSONField(ordinal = 3)
    private int[] api_ship_lv;

    @JSONField(ordinal = 4)
    private int[] api_nowhps;

    @JSONField(ordinal = 5)
    private int[] api_maxhps;

    @JSONField(ordinal = 6)
    private int api_midnight_flag = 0;

    @JSONField(ordinal = 7)
    private int[][] api_eSlot;

    @JSONField(ordinal = 8)
    private int[][] api_eKyouka;

    @JSONField(ordinal = 9)
    private int[][] api_fParam;

    @JSONField(ordinal = 10)
    private int[][] api_eParam;

    /*
     * 索敵成否 [0]=味方, [1]=敵 1=成功, 2=成功(未帰還機あり), 3=失敗+未帰還, 4=失敗, 5=成功(艦載機使用せず),
     * 6=失敗(艦載機使用せず)
     */
    @JSONField(ordinal = 11)
    private int[] api_search;

    /*
     * 陣形/交戦形態 [0]=味方, [1]=敵, [2]=交戦形態 [0|1]：1=単縦陣 2=複縦陣, 3=輪形陣, 4=梯形陣, 5=単横陣
     * [2]：1=同航戦, 2=反航戦, 3=T字有利, 4=T字不利
     */
    @JSONField(ordinal = 12)
    private int[] api_formation;

    /** 航空戦flag */
    @JSONField(ordinal = 13)
    private int[] api_stage_flag;

    /** 航空戦情報 */
    @JSONField(ordinal = 14)
    private KouKuResult api_kouku;

    @JSONField(ordinal = 15)
    private int api_support_flag;

    @JSONField(ordinal = 16, serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Object api_support_info;

    @JSONField(ordinal = 17)
    private int api_opening_flag;

    @JSONField(ordinal = 18, serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Object api_opening_atack;

    /** 炮击战flag */
    @JSONField(ordinal = 19)
    private int[] api_hourai_flag;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private final List<HougekiResult> hougekiResults = Lists.newArrayListWithCapacity(MAX_SHELLING_ROUND);

    /** 炮击第一轮 */
    @JSONField(name = "api_hougeki1", ordinal = 20, serialzeFeatures = SerializerFeature.WriteMapNullValue)
    public HougekiResult getHougekiResult1() {
        return hougekiResults.isEmpty() ? null : hougekiResults.iterator().next();
    }

    public void addHougekiResult(HougekiResult hougekiResult){
        this.hougekiResults.add( hougekiResult);
    }

    /** 炮击第二轮 */
    @JSONField(name = "api_hougeki2", ordinal = 21, serialzeFeatures = SerializerFeature.WriteMapNullValue)
    public HougekiResult getHougekiResult2() {
        return hougekiResults.size() > 1 ? hougekiResults.get(1) : null;
    }

    /** 炮击第三轮 */
    @JSONField(name = "api_hougeki3",ordinal = 22, serialzeFeatures = SerializerFeature.WriteMapNullValue)
    public HougekiResult getHougekiResult3() {
        return hougekiResults.size() > 2 ? hougekiResults.get(2) : null;
    }

    /** 闭幕雷击 */
    @JSONField(ordinal = 23, serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Object api_raigeki;

    private BattleSimulationResult() {
        this.api_ship_ke = defaultIfNull(this.api_ship_ke, new int[7]);
        Arrays.fill(this.api_ship_ke, -1);

        this.api_ship_lv = defaultIfNull(this.api_ship_lv, new int[7]);
        Arrays.fill(this.api_ship_lv, -1);

        this.api_nowhps = defaultIfNull(this.api_nowhps, new int[13]);
        Arrays.fill(this.api_nowhps, -1);

        this.api_maxhps = defaultIfNull(this.api_maxhps, new int[13]);
        Arrays.fill(this.api_maxhps, -1);

        this.api_eSlot = defaultIfNull(this.api_eSlot, new int[6][5]);
        Arrays.stream(this.api_eSlot).forEach(values -> Arrays.fill(values, -1));

        this.api_eKyouka = defaultIfNull(this.api_eKyouka, new int[6][4]);
        Arrays.stream(this.api_eKyouka).forEach(values -> Arrays.fill(values, 0));

        this.api_fParam = defaultIfNull(this.api_fParam, new int[6][4]);
        Arrays.stream(this.api_fParam).forEach(values -> Arrays.fill(values, 0));

        this.api_eParam = defaultIfNull(this.api_eParam, new int[6][4]);
        Arrays.stream(this.api_eParam).forEach(values -> Arrays.fill(values, 0));
    }

    public BattleSimulationResult(MemberDeckPort memberDeckPort, EnemyDeckPort enemyDeckPort) {
        this();
        this.api_dock_id = memberDeckPort.getDeckId();

        List<MemberShip> memberShips = memberDeckPort.getShips();
        List<EnemyShip> enemyShips = enemyDeckPort.getEnemyShips();

        Stream.iterate(1, i -> ++i).limit(enemyShips.size()).forEach(i -> this.api_ship_ke[i] = enemyShips.get(i - 1).getShip().getShipId());
        Stream.iterate(1, i -> ++i).limit(enemyShips.size()).forEach(i -> this.api_ship_lv[i] = 1);
        Stream.iterate(1, i -> ++i).limit(memberShips.size()).forEach(i -> this.api_nowhps[i] = memberShips.get(i - 1).getNowHp());
        Stream.iterate(7, i -> ++i).limit(enemyShips.size()).forEach(i -> this.api_nowhps[i] = enemyShips.get(i - 7).getShip().getTaik().getMinValue());
        Stream.iterate(1, i -> ++i).limit(memberShips.size()).forEach(i -> this.api_maxhps[i] = memberShips.get(i - 1).getMaxHp());
        Stream.iterate(7, i -> ++i).limit(enemyShips.size()).forEach(i -> this.api_maxhps[i] = enemyShips.get(i - 7).getShip().getTaik().getMaxValue());

        this.api_midnight_flag = 0;

        Stream.iterate(0, i -> ++i).limit(enemyShips.size()).forEach(i -> {
            int[] slots = enemyShips.get(i).getSlotItems().stream().mapToInt(AbstractSlotItem::getSlotItemId).toArray();
            slots = Ints.ensureCapacity(slots, 5, 0);
            Arrays.fill(slots, ArrayUtils.indexOf(slots, 0), 5, -1);
            this.api_eSlot[i] = slots;
        });
    }

    public int getApi_dock_id() {
        return api_dock_id;
    }

    public void setApi_dock_id(int api_dock_id) {
        this.api_dock_id = api_dock_id;
    }

    public int[] getApi_ship_ke() {
        return api_ship_ke;
    }

    public void setApi_ship_ke(int[] api_ship_ke) {
        this.api_ship_ke = api_ship_ke;
    }

    public int[] getApi_ship_lv() {
        return api_ship_lv;
    }

    public void setApi_ship_lv(int[] api_ship_lv) {
        this.api_ship_lv = api_ship_lv;
    }

    public int[] getApi_nowhps() {
        return api_nowhps;
    }

    public void setApi_nowhps(int[] api_nowhps) {
        this.api_nowhps = api_nowhps;
    }

    public int[] getApi_maxhps() {
        return api_maxhps;
    }

    public void setApi_maxhps(int[] api_maxhps) {
        this.api_maxhps = api_maxhps;
    }

    public int getApi_midnight_flag() {
        return api_midnight_flag;
    }

    public void setApi_midnight_flag(int api_midnight_flag) {
        this.api_midnight_flag = api_midnight_flag;
    }

    public int[][] getApi_eSlot() {
        return api_eSlot;
    }

    public void setApi_eSlot(int[][] api_eSlot) {
        this.api_eSlot = api_eSlot;
    }

    public int[][] getApi_eKyouka() {
        return api_eKyouka;
    }

    public void setApi_eKyouka(int[][] api_eKyouka) {
        this.api_eKyouka = api_eKyouka;
    }

    public int[][] getApi_fParam() {
        return api_fParam;
    }

    public void setApi_fParam(int[][] api_fParam) {
        this.api_fParam = api_fParam;
    }

    public int[][] getApi_eParam() {
        return api_eParam;
    }

    public void setApi_eParam(int[][] api_eParam) {
        this.api_eParam = api_eParam;
    }

    public int[] getApi_search() {
        return api_search;
    }

    public void setApi_search(int[] api_search) {
        this.api_search = api_search;
    }

    public int[] getApi_formation() {
        return api_formation;
    }

    public void setApi_formation(int[] api_formation) {
        this.api_formation = api_formation;
    }

    public int[] getApi_stage_flag() {
        return api_stage_flag;
    }

    public void setApi_stage_flag(int[] api_stage_flag) {
        this.api_stage_flag = api_stage_flag;
    }

    public KouKuResult getApi_kouku() {
        return api_kouku;
    }

    public void setApi_kouku(KouKuResult api_kouku) {
        this.api_kouku = api_kouku;
    }

    public int getApi_support_flag() {
        return api_support_flag;
    }

    public void setApi_support_flag(int api_support_flag) {
        this.api_support_flag = api_support_flag;
    }

    public Object getApi_support_info() {
        return api_support_info;
    }

    public void setApi_support_info(Object api_support_info) {
        this.api_support_info = api_support_info;
    }

    public int getApi_opening_flag() {
        return api_opening_flag;
    }

    public void setApi_opening_flag(int api_opening_flag) {
        this.api_opening_flag = api_opening_flag;
    }

    public Object getApi_opening_atack() {
        return api_opening_atack;
    }

    public void setApi_opening_atack(Object api_opening_atack) {
        this.api_opening_atack = api_opening_atack;
    }

    public int[] getApi_hourai_flag() {
        return api_hourai_flag;
    }

    public void setApi_hourai_flag(int[] api_hourai_flag) {
        this.api_hourai_flag = api_hourai_flag;
    }

    public Object getApi_raigeki() {
        return api_raigeki;
    }

    public void setApi_raigeki(Object api_raigeki) {
        this.api_raigeki = api_raigeki;
    }
}
