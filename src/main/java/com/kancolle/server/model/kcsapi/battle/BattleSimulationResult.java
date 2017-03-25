/**
 *
 */
package com.kancolle.server.model.kcsapi.battle;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.kancolle.server.model.kcsapi.battle.houku.KouKuResult;
import com.kancolle.server.model.kcsapi.battle.ship.HougekiResult;
import com.kancolle.server.model.po.deckport.MemberDeckPort;
import com.kancolle.server.model.po.ship.IShip;
import com.kancolle.server.model.po.ship.MemberShip;
import com.kancolle.server.model.po.ship.UnderSeaShip;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 */
@JsonPropertyOrder(value = {
        "api_dock_id", "api_ship_ke", "api_ship_lv", "api_nowhps",
        "api_maxhps", "api_midnight_flag", "api_eSlot", "api_eKyouka",
        "api_fParam", "api_eParam", "api_search", "api_formation",
        "api_stage_flag", "api_kouku", "api_support_flag", "api_support_info",
        "api_opening_flag", "api_opening_atack", "api_hourai_flag", "api_hougeki1",
        "api_hougeki2", "api_hougeki3", "api_raigeki"
})
public class BattleSimulationResult {

    private static final int MAX_SHELLING_ROUND = 3;
    public static final int FIRST_START_INDEX = 1;
    public static final int SECOND_START_INDEX = 7;

    private static final int[] PARAM = new int[]{0, 0, 0, 0};

    @JsonProperty(value = "api_dock_id")
    private int api_dock_id;

    /**
     * 敌舰队舰船ID，-1开始，-1结束，空舰船ID为-1
     */
    @JsonProperty(value = "api_ship_ke")
    private int[] api_ship_ke;

    @JsonProperty(value = "api_ship_lv")
    private int[] api_ship_lv;

    @JsonProperty(value = "api_nowhps")
    private int[] api_nowhps;

    @JsonProperty(value = "api_maxhps")
    private int[] api_maxhps;

    @JsonProperty(value = "api_midnight_flag")
    private int api_midnight_flag = 0;

    @JsonProperty(value = "api_eSlot")
    private int[][] api_eSlot;

    @JsonProperty(value = "api_eKyouka")
    private int[][] api_eKyouka;

    /*
     * 味方艦船基礎ステータス [0]=火力, [1]=雷装, [2]=対空, [3]=装甲
     */
    @JsonProperty(value = "api_fParam")
    private int[][] api_fParam;
    /*
     * 敵艦船基礎ステータス [0]=火力, [1]=雷装, [2]=対空, [3]=装甲
     */
    @JsonProperty(value = "api_eParam")
    private int[][] api_eParam;

    /*
     * 索敵成否 [0]=味方, [1]=敵 1=成功, 2=成功(未帰還機あり), 3=失敗+未帰還, 4=失敗, 5=成功(艦載機使用せず),
     * 6=失敗(艦載機使用せず)
     */
    @JsonProperty(value = "api_search")
    private int[] api_search;

    /*
     * 陣形/交戦形態 [0]=味方, [1]=敵, [2]=交戦形態 [0|1]：1=単縦陣 2=複縦陣, 3=輪形陣, 4=梯形陣, 5=単横陣
     * [2]：1=同航戦, 2=反航戦, 3=T字有利, 4=T字不利
     */
    @JsonProperty(value = "api_formation")
    private int[] api_formation;

    /**
     * 航空戦flag
     */
    @JsonProperty(value = "api_stage_flag")
    private int[] api_stage_flag;

    /**
     * 航空戦情報
     */
    @JsonProperty(value = "api_kouku")
    private KouKuResult api_kouku;

    @JsonProperty(value = "api_support_flag")
    private int api_support_flag;

    @JSONField(ordinal = 16, serialzeFeatures = SerializerFeature.WriteMapNullValue)
    @JsonProperty(value = "api_support_info")
    private Object api_support_info;

    @JsonProperty(value = "api_opening_flag")
    private int api_opening_flag;

    @JSONField(ordinal = 18, serialzeFeatures = SerializerFeature.WriteMapNullValue)
    @JsonProperty(value = "api_opening_atack")
    private Object api_opening_atack;

    /**
     * 炮击战flag
     */
    @JsonProperty(value = "api_hourai_flag")
    private int[] api_hourai_flag;

    @JsonIgnore
    private transient List<HougekiResult> hougekiResults = Lists.newArrayListWithExpectedSize(3);

    @JsonProperty(value = "api_hougeki1")
    private HougekiResult api_hougeki1;

    @JsonProperty(value = "api_hougeki2")
    private HougekiResult api_hougeki2;

    @JsonProperty(value = "api_hougeki3")
    private HougekiResult api_hougeki3;

    /**
     * 闭幕雷击
     */
    @JSONField(ordinal = 23, serialzeFeatures = SerializerFeature.WriteMapNullValue)
    @JsonProperty(value = "api_raigeki")
    private Object api_raigeki;

    private BattleSimulationResult() {
        this.api_ship_ke = new int[7];
        Arrays.fill(this.api_ship_ke, -1);

        this.api_ship_lv = new int[7];
        Arrays.fill(this.api_ship_lv, -1);

        this.api_nowhps = new int[13];
        Arrays.fill(this.api_nowhps, -1);

        this.api_maxhps = new int[13];
        Arrays.fill(this.api_maxhps, -1);

        this.api_eSlot = new int[6][5];
        Arrays.stream(this.api_eSlot).forEach(values -> Arrays.fill(values, -1));

        this.api_eKyouka = new int[6][4];
        Arrays.stream(this.api_eKyouka).forEach(values -> Arrays.fill(values, 0));

//        this.api_fParam = new int[6][4];
//        Arrays.stream(this.api_fParam).forEach(values -> Arrays.fill(values, 0));
//
//        this.api_eParam = new int[6][4];
//        Arrays.stream(this.api_eParam).forEach(values -> Arrays.fill(values, 0));
    }

    public BattleSimulationResult(final MemberDeckPort memberDeckPort, final List<UnderSeaShip> underSeaShips) {
        this();
        this.api_dock_id = memberDeckPort.getDeckId();

        List<MemberShip> memberShips = memberDeckPort.getShips();

        int enemyShipSize = underSeaShips.size();
        int memberShipSize = memberShips.size();

//        int[] ke = underSeaShips.stream().map(IShip::getShip).mapToInt(Ship::getShipId).toArray();
//        System.arraycopy(ke, 0, this.api_ship_ke, FIRST_START_INDEX, ke.length);
//
//        int[] lv = underSeaShips.stream().mapToInt(IShip::getNowLv).toArray();
//        System.arraycopy(lv, 0, this.api_ship_lv, FIRST_START_INDEX, lv.length);
//
//        int[] nowHps = memberShips.stream().mapToInt(IShip::getNowHp).toArray();
//        System.arraycopy(nowHps, 0, this.api_nowhps, FIRST_START_INDEX, nowHps.length);
//        int[] enowHps = underSeaShips.stream().mapToInt(IShip::getNowHp).toArray();
//        System.arraycopy(enowHps, 0, this.api_nowhps, SECOND_START_INDEX, nowHps.length);
//
//        int[] maxHps = memberShips.stream().mapToInt(IShip::getMaxHp).toArray();
//        System.arraycopy(maxHps, 0, this.api_maxhps, FIRST_START_INDEX, nowHps.length);
//        int[] emaxHps = underSeaShips.stream().mapToInt(IShip::getMaxHp).toArray();
//        System.arraycopy(emaxHps, 0, this.api_maxhps, SECOND_START_INDEX, nowHps.length);

        IntStream.rangeClosed(FIRST_START_INDEX, enemyShipSize).forEach(i -> this.api_ship_ke[i] = underSeaShips.get(i - FIRST_START_INDEX).getShip().getShipId());
        IntStream.rangeClosed(FIRST_START_INDEX, enemyShipSize).forEach(i -> this.api_ship_lv[i] = underSeaShips.get(i - FIRST_START_INDEX).getShip().getLv());
        IntStream.rangeClosed(FIRST_START_INDEX, memberShipSize).forEach(i -> this.api_nowhps[i] = memberShips.get(i - FIRST_START_INDEX).getNowHp());
        IntStream.range(SECOND_START_INDEX, SECOND_START_INDEX + enemyShipSize).forEach(i -> this.api_nowhps[i] = underSeaShips.get(i - SECOND_START_INDEX).getNowHp());
        IntStream.rangeClosed(FIRST_START_INDEX, memberShipSize).forEach(i -> this.api_maxhps[i] = memberShips.get(i - FIRST_START_INDEX).getMaxHp());
        IntStream.range(SECOND_START_INDEX, SECOND_START_INDEX + enemyShipSize).forEach(i -> this.api_maxhps[i] = underSeaShips.get(i - SECOND_START_INDEX).getMaxHp());

        this.api_midnight_flag = 0;

        IntStream.range(0, enemyShipSize).forEach(i -> {
            int[] slots = underSeaShips.get(i).getSlotItems().stream().mapToInt(AbstractSlotItem::getSlotItemId).toArray();
            slots = Ints.ensureCapacity(slots, 5, 0);
            Arrays.fill(slots, ArrayUtils.indexOf(slots, 0), 5, -1);
            this.api_eSlot[i] = slots;
        });

        this.api_fParam = Utils.getParam(memberShips);
        this.api_eParam = Utils.getParam(underSeaShips);
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

    public HougekiResult getApi_hougeki1() {
        return api_hougeki1;
    }

    public void setApi_hougeki1(HougekiResult api_hougeki1) {
        this.api_hougeki1 = api_hougeki1;
    }

    public HougekiResult getApi_hougeki2() {
        return api_hougeki2;
    }

    public void setApi_hougeki2(HougekiResult api_hougeki2) {
        this.api_hougeki2 = api_hougeki2;
    }

    public HougekiResult getApi_hougeki3() {
        return api_hougeki3;
    }

    public void setApi_hougeki3(HougekiResult api_hougeki3) {
        this.api_hougeki3 = api_hougeki3;
    }

    public void addHougekiResult(HougekiResult hougekiResult) {
        this.hougekiResults.add(hougekiResult);
        switch (this.hougekiResults.size()) {
            case 1:
                this.api_hougeki1 = hougekiResult;
            case 2:
                this.api_hougeki2 = hougekiResult;
            case 3:
                this.api_hougeki3 = hougekiResult;
            default:
        }
    }

    private static class Utils {

        private static int[][] getParam(List<? extends IShip> ships) {
            final int[][] param = new int[6][];

            final int shipSize = ships.size();

            IntStream.range(0, shipSize).forEach(i -> {
                IShip ship = ships.get(i);
                param[i] = new int[]{
                        ship.getShipKaryoku(),
                        ship.getShipRaisou(),
                        ship.getShipTaiku(),
                        ship.getShipSoukou()};
            });

            IntStream.range(shipSize, param.length).forEach(i -> param[i] = PARAM);
            return param;
        }
    }
}
