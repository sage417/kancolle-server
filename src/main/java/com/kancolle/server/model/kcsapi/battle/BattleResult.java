/**
 *
 */
package com.kancolle.server.model.kcsapi.battle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.kcsapi.battle.enemy.EnemyInfo;
import com.kancolle.server.model.kcsapi.battle.event.GetEventItem;
import com.kancolle.server.model.kcsapi.battle.event.GetShip;
import com.kancolle.server.model.kcsapi.battle.event.GetSlotItem;
import com.kancolle.server.model.kcsapi.battle.event.LandingHp;
import com.kancolle.server.model.kcsapi.useitem.item.GetItem;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年9月2日
 */
@JsonPropertyOrder({
        "api_ship_id", "api_win_rank", "api_get_exp", "api_mvp",
        "api_member_lv", "api_member_exp", "api_get_base_exp", "api_get_ship_exp",
        "api_get_exp_lvup", "api_dests", "api_destsf", "api_lost_flag",
        "api_quest_name", "api_quest_level", "api_enemy_info", "api_first_clear", "api_mapcell_incentive",
        "api_get_flag", "api_get_useitem", "api_get_ship", "api_get_slotitem",
        "api_get_eventitem", "api_get_eventflag", "api_get_exmap_rate", "api_get_exmap_useitem_id"})
public class BattleResult {

    /* 敌舰队Id */
    @JsonProperty(value = "api_ship_id")
    private long[] ship_id;

    @JsonProperty(value = "api_win_rank")
    private int win_rank;

    @JsonProperty(value = "api_get_exp")
    /* 提督获取exp */
    private int get_exp;

    @JsonProperty(value = "api_mvp")
    /* mvp 索引  从1开始 -1表示不存在 */
    private int mvp;

    private int member_lv;

    private long member_exp;

    /* 基本经验值 */
    private int base_exp;

    /* 各舰获得经验值 */
    private int[] ship_exp;

    private long[][] exp_lvup;

    private int dests;

    private int destsf;

    private int lost_flag;

    private String quest_name;

    private int quest_level;

    private List<EnemyInfo> enemy_info;

    private int first_clear;

    private int mapcell_incentive;

    private int get_flag;

    private List<GetItem> get_item;

    private List<GetShip> get_ship;

    private List<GetSlotItem> get_slotitem;

    private List<GetEventItem> get_eventitem;

    private int eventflag;

    private int exmap_rate;

    private int exmap_useitem_id;

    private LandingHp landing_hp;

    public long[] getShip_id() {
        return ship_id;
    }

    public void setShip_id(long[] ship_id) {
        this.ship_id = ship_id;
    }

    public int getWin_rank() {
        return win_rank;
    }

    public void setWin_rank(int win_rank) {
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
        this.ship_exp = ship_exp;
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

    public int getLost_flag() {
        return lost_flag;
    }

    public void setLost_flag(int lost_flag) {
        this.lost_flag = lost_flag;
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

    public List<EnemyInfo> getEnemy_info() {
        return enemy_info;
    }

    public void setEnemy_info(List<EnemyInfo> enemy_info) {
        this.enemy_info = enemy_info;
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

    public int getGet_flag() {
        return get_flag;
    }

    public void setGet_flag(int get_flag) {
        this.get_flag = get_flag;
    }

    public List<GetItem> getGet_item() {
        return get_item;
    }

    public void setGet_item(List<GetItem> get_item) {
        this.get_item = get_item;
    }

    public List<GetShip> getGet_ship() {
        return get_ship;
    }

    public void setGet_ship(List<GetShip> get_ship) {
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
