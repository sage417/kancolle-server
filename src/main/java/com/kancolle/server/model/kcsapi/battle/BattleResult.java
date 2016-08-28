/**
 *
 */
package com.kancolle.server.model.kcsapi.battle;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.ImmutableList;
import com.kancolle.server.model.kcsapi.battle.enemy.EnemyInfo;
import com.kancolle.server.model.kcsapi.battle.event.BattleGetShipResult;
import com.kancolle.server.model.kcsapi.battle.event.GetEventItem;
import com.kancolle.server.model.kcsapi.battle.event.GetSlotItem;
import com.kancolle.server.model.kcsapi.battle.event.LandingHp;
import com.kancolle.server.model.kcsapi.useitem.item.GetItem;
import com.kancolle.server.model.po.deckport.UnderSeaDeckPort;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年9月2日
 */
@JsonPropertyOrder({
        "api_ship_id", "api_win_rank", "api_get_exp", "api_mvp",
        "api_member_lv", "api_member_exp", "api_get_base_exp", "api_get_ship_exp",
        "api_get_exp_lvup", "api_dests", "api_destsf", "api_lost_flag",
        "api_quest_name", "api_quest_level", "api_enemy_info", "api_first_clear",
        "api_mapcell_incentive", "api_get_flag", "api_get_useitem", "api_get_ship",
        "api_get_slotitem", "api_get_eventitem", "api_get_eventflag", "api_get_exmap_rate",
        "api_get_exmap_useitem_id", "api_landing_hp"
})
public class BattleResult {

    public static final int EXMAP_USEITEM_ID = 57;

    public static final ImmutableList<Double> EXP_AUG = ImmutableList.of(1.2d, 1.2d, 1d, 0.8d, 0.6d, 0.5d);

    public static final ImmutableList<Integer> COND_AUG = ImmutableList.of(4, 3, 2, 1, 0, 0);

    public enum WIN_RANK {
        SS, S, A, B, C, D, E
    }

    public static final int GET_NONE = 0;

    public static final int GET_USEITEM = 1;

    public static final int GET_SHIP = 2;

    public static final int GET_SLOTITEM = 4;

    /* ---------------- 缓存 --------------- */

    private static final int[] GET_FLAG_ARR_NONE = {0, 0, 0};

    private static final int[] GET_FLAG_ARR_USEITEM = {1, 0, 0};
    private static final int[] GET_FLAG_ARR_SHIP = {0, 1, 0};
    private static final int[] GET_FLAG_ARR_SLOTITEM = {0, 0, 1};

    private static final int[] GET_FLAG_ARR_USEITEM_AND_SHIP = {1, 1, 0};
    private static final int[] GET_FLAG_ARR_USEITEM_AND_SLOTITEM = {1, 0, 1};
    private static final int[] GET_FLAG_ARR_SHIP_AND_SLOTITEM = {0, 1, 1};

    private static final int[] GET_FLAG_ARR_ALL = {1, 1, 1};

    /* ---------------- 缓存 --------------- */


    /* 敌舰队Id */
    @JsonProperty(value = "api_ship_id")
    @JSONField(name = "api_ship_id", ordinal = 1)
    private int[] ship_id;

    @JsonProperty(value = "api_win_rank")
    @JSONField(name = "api_win_rank", ordinal = 2)
    private String win_rank;

    /* 提督获取exp */
    @JsonProperty(value = "api_get_exp")
    @JSONField(name = "api_get_exp", ordinal = 3)
    private int get_exp;

    /* mvp 索引  从1开始 -1表示不存在 */
    @JsonProperty(value = "api_mvp")
    @JSONField(name = "api_mvp", ordinal = 4)
    private int mvp;

    @JsonProperty(value = "api_member_lv")
    @JSONField(name = "api_member_lv", ordinal = 5)
    private int member_lv;

    @JsonProperty(value = "api_member_exp")
    @JSONField(name = "api_member_exp", ordinal = 6)
    private long member_exp;

    /* 基本经验值 */
    @JsonProperty(value = "api_get_base_exp")
    @JSONField(name = "api_get_base_exp", ordinal = 7)
    private int base_exp;

    /* 各舰获得经验值 */
    @JsonProperty(value = "api_get_ship_exp")
    @JSONField(name = "api_get_ship_exp", ordinal = 8)
    private int[] ship_exp;

    /* 各艦の[0]獲得前経験値, [1]次のレベルの経験値(Lv99|150の場合存在せず), (レベルアップしたなら)[2]その次のレベルの経験値, ... */
    @JsonProperty(value = "api_get_exp_lvup")
    @JSONField(name = "api_get_exp_lvup", ordinal = 9)
    private long[][] exp_lvup;

    /* 敵艦撃沈数 */
    @JsonProperty(value = "api_dests")
    @JSONField(name = "api_dests", ordinal = 10)
    private int dests;

    /* 旗艦撃沈フラグ */
    @JsonProperty(value = "api_destsf")
    @JSONField(name = "api_destsf", ordinal = 11)
    private int destsf;

    /* 味方艦撃沈フラグ？-1から始まる */
    @JsonProperty(value = "api_lost_flag")
    @JSONField(name = "api_lost_flag", ordinal = 12)
    private int[] lost_flag;

    @JsonProperty(value = "api_quest_name")
    @JSONField(name = "api_quest_name", ordinal = 13)
    private String quest_name;

    @JsonProperty(value = "api_quest_level")
    @JSONField(name = "api_quest_level", ordinal = 14)
    private int quest_level;

    @JsonProperty(value = "api_enemy_info")
    @JSONField(name = "api_enemy_info", ordinal = 15)
    private EnemyInfo enemy_info;

    /* 初回クリアフラグ(EO海域攻略時も1) */
    @JsonProperty(value = "api_first_clear")
    @JSONField(name = "api_first_clear", ordinal = 16)
    private int first_clear;

    /* 航空偵察作戦報酬獲得フラグ　0=なし, 1=獲得 */
    @JsonProperty(value = "api_mapcell_incentive")
    @JSONField(name = "api_mapcell_incentive", ordinal = 17)
    private int mapcell_incentive;

    /* 入手フラグ [0]=アイテム, [1]=艦娘 */
    @JsonProperty(value = "api_get_flag")
    @JSONField(name = "api_get_flag", ordinal = 18)
    private int[] get_flag;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "api_get_useitem")
    @JSONField(name = "api_get_useitem", ordinal = 19)
    private List<GetItem> get_useitem;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "api_get_ship")
    @JSONField(name = "api_get_ship", ordinal = 20)
    private List<BattleGetShipResult> get_ship;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "api_get_slotitem")
    @JSONField(name = "api_get_slotitem", ordinal = 21)
    private List<GetSlotItem> get_slotitem;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "api_get_eventitem")
    @JSONField(name = "api_get_eventitem", ordinal = 22)
    private List<GetEventItem> get_eventitem;

    @JsonProperty(value = "api_get_eventflag")
    @JSONField(name = "api_get_eventflag", ordinal = 23)
    private int eventflag;

    /* EO海域攻略時：獲得戦果(文字列) それ以外は0(数値) */
    @JsonProperty(value = "api_get_exmap_rate")
    @JSONField(name = "api_get_exmap_rate", ordinal = 24)
    private int exmap_rate;

    /* EO海域攻略時：取得アイテムID(文字列) "57"=勲章　それ以外は0(数値) */
    @JsonProperty(value = "api_get_exmap_useitem_id")
    @JSONField(name = "api_get_exmap_useitem_id", ordinal = 25)
    private int exmap_useitem_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "api_landing_hp")
    @JSONField(name = "api_landing_hp", ordinal = 26)
    private LandingHp landing_hp;

    public BattleResult() {
        this.lost_flag = new int[7];
        Arrays.fill(lost_flag, -1);
        this.ship_exp = new int[7];
        Arrays.fill(ship_exp, -1);
    }

    public void generateGetFlag(int getFlag) {
        switch (getFlag) {
            case GET_NONE:
                this.get_flag = GET_FLAG_ARR_NONE;
                break;
            case GET_USEITEM:
                this.get_flag = GET_FLAG_ARR_USEITEM;
                break;
            case GET_SHIP:
                this.get_flag = GET_FLAG_ARR_SHIP;
                break;
            case GET_SLOTITEM:
                this.get_flag = GET_FLAG_ARR_SLOTITEM;
                break;
            case GET_USEITEM | GET_SHIP:
                this.get_flag = GET_FLAG_ARR_USEITEM_AND_SHIP;
                break;
            case GET_USEITEM | GET_SLOTITEM:
                this.get_flag = GET_FLAG_ARR_USEITEM_AND_SLOTITEM;
                break;
            case GET_SHIP | GET_SLOTITEM:
                this.get_flag = GET_FLAG_ARR_SHIP_AND_SLOTITEM;
                break;
            case GET_USEITEM | GET_SHIP | GET_SLOTITEM:
                this.get_flag = GET_FLAG_ARR_ALL;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void addExp_lvup(long[] exp_lvup) {
        this.exp_lvup = ArrayUtils.add(this.exp_lvup, exp_lvup);
    }

    public void setWinRank(String rank) {
        this.win_rank = rank;
    }

    public int[] getShip_id() {
        return ship_id;
    }

    public void setShip_id(int[] ship_id) {
        this.ship_id = ship_id;
    }

    public String getWin_rank() {
        return win_rank;
    }

    public void setWin_rank(String win_rank) {
        this.win_rank = win_rank;
    }

    public int getGet_exp() {
        return get_exp;
    }

    public void setGet_exp(int get_exp) {
        this.get_exp = get_exp;
    }

    public int getMvp() {
        return mvp;
    }

    public void setMvp(int mvp) {
        this.mvp = mvp;
    }

    public int getMember_lv() {
        return member_lv;
    }

    public void setMember_lv(int member_lv) {
        this.member_lv = member_lv;
    }

    public long getMember_exp() {
        return member_exp;
    }

    public void setMember_exp(long member_exp) {
        this.member_exp = member_exp;
    }

    public int getBase_exp() {
        return base_exp;
    }

    public void setBase_exp(int base_exp) {
        this.base_exp = base_exp;
    }

    public int[] getShip_exp() {
        return ship_exp;
    }

    public void setShip_exp(int[] ship_exp) {
        throw new UnsupportedOperationException();
    }

    public long[][] getExp_lvup() {
        return exp_lvup;
    }

    public void setExp_lvup(long[][] exp_lvup) {
        this.exp_lvup = exp_lvup;
    }

    public int getDests() {
        return dests;
    }

    public void setDests(int dests) {
        this.dests = dests;
    }

    public int getDestsf() {
        return destsf;
    }

    public void setDestsf(int destsf) {
        this.destsf = destsf;
    }

    public int[] getLost_flag() {
        return lost_flag;
    }

    public void setLost_flag(int[] lost_flag) {
        throw new UnsupportedOperationException();
    }

    public String getQuest_name() {
        return quest_name;
    }

    public void setQuest_name(String quest_name) {
        this.quest_name = quest_name;
    }

    public int getQuest_level() {
        return quest_level;
    }

    public void setQuest_level(int quest_level) {
        this.quest_level = quest_level;
    }

    public EnemyInfo getEnemy_info() {
        return enemy_info;
    }

    public void setEnemy_info(EnemyInfo enemy_info) {
        this.enemy_info = enemy_info;
    }

    public void setEnemy_info(UnderSeaDeckPort underSeaDeckPort) {
        this.enemy_info = new EnemyInfo();
        this.enemy_info.setLv(underSeaDeckPort.getLv());
        this.enemy_info.setRank(underSeaDeckPort.getRank());
        this.enemy_info.setDeckName(underSeaDeckPort.getDeckName());
    }

    public int getFirst_clear() {
        return first_clear;
    }

    public void setFirst_clear(int first_clear) {
        this.first_clear = first_clear;
    }

    public int getMapcell_incentive() {
        return mapcell_incentive;
    }

    public void setMapcell_incentive(int mapcell_incentive) {
        this.mapcell_incentive = mapcell_incentive;
    }

    public int[] getGet_flag() {
        return get_flag;
    }

    public void setGet_flag(int[] get_flag) {
        this.get_flag = get_flag;
    }

    public List<GetItem> getGet_useitem() {
        return get_useitem;
    }

    public void setGet_useitem(List<GetItem> get_useitem) {
        this.get_useitem = get_useitem;
    }

    public List<BattleGetShipResult> getGet_ship() {
        return get_ship;
    }

    public void setGet_ship(List<BattleGetShipResult> get_ship) {
        this.get_ship = get_ship;
    }

    public List<GetSlotItem> getGet_slotitem() {
        return get_slotitem;
    }

    public void setGet_slotitem(List<GetSlotItem> get_slotitem) {
        this.get_slotitem = get_slotitem;
    }

    public List<GetEventItem> getGet_eventitem() {
        return get_eventitem;
    }

    public void setGet_eventitem(List<GetEventItem> get_eventitem) {
        this.get_eventitem = get_eventitem;
    }

    public int getEventflag() {
        return eventflag;
    }

    public void setEventflag(int eventflag) {
        this.eventflag = eventflag;
    }

    public int getExmap_rate() {
        return exmap_rate;
    }

    public void setExmap_rate(int exmap_rate) {
        this.exmap_rate = exmap_rate;
    }

    public int getExmap_useitem_id() {
        return exmap_useitem_id;
    }

    public void setExmap_useitem_id(int exmap_useitem_id) {
        this.exmap_useitem_id = exmap_useitem_id;
    }

    public LandingHp getLanding_hp() {
        return landing_hp;
    }

    public void setLanding_hp(LandingHp landing_hp) {
        this.landing_hp = landing_hp;
    }
}
