/**
 *
 */
package com.kancolle.server.model.kcsapi.battle.map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.kcsapi.battle.plane.AirSearch;

/**
 * @author J.K.SAGE
 * @Date 2015年8月20日
 */
@JsonPropertyOrder({
        "api_rashin_flg", "api_rashin_id", "api_maparea_id", "api_mapinfo_no",
        "api_no", "api_color_no", "api_event_id", "api_event_kind",
        "api_next", "api_bosscell_no", "api_bosscomp", "api_airsearch",
        "api_ration_flag", "api_itemget"
})
public class MapStartResult {

    /** 随机条件 **/

    // 罗盘
    @JsonProperty("api_rashin_flg")
    private int rashinFlag;

    // 妖精
    @JsonProperty("api_rashin_id")
    private int rashinId;

    /** 随机条件 **/

    /** 固定条件 **/

    @JsonProperty("api_maparea_id")
    private int mapareaId;

    @JsonProperty("api_mapinfo_no")
    private int mapinfoId;

    @JsonProperty("api_no")
    private int no;

    @JsonProperty("api_color_no")
    private int colorNo;

    @JsonProperty("api_event_id")
    private int eventId;

    @JsonProperty("api_event_kind")
    private int eventKind;

    @JsonProperty("api_next")
    private int next;

    @JsonProperty("api_bosscell_no")
    private int bossCellNo;

    @JsonProperty("api_bosscomp")
    private int bossComp;

    /** 固定条件 **/

    /** 外部条件 **/
    @JsonProperty("api_airsearch")
    private AirSearch airSearch = AirSearch.NO_AIR_SEARCH;

    // 战斗食粮
    @JsonProperty("api_ration_flag")
    private int rationFlag;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("api_eventmap")
    private MapEventMap eventMap;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("api_itemget")
    private MapItemGet itemGet;
    /** 外部条件 **/

    public int getRashinFlag() {
        return rashinFlag;
    }

    public void setRashinFlag(int rashinFlag) {
        this.rashinFlag = rashinFlag;
    }

    public int getRashinId() {
        return rashinId;
    }

    public void setRashinId(int rashinId) {
        this.rashinId = rashinId;
    }

    public int getMapareaId() {
        return mapareaId;
    }

    public void setMapareaId(int mapareaId) {
        this.mapareaId = mapareaId;
    }

    public int getMapinfoId() {
        return mapinfoId;
    }

    public void setMapinfoId(int mapinfoId) {
        this.mapinfoId = mapinfoId;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getColorNo() {
        return colorNo;
    }

    public void setColorNo(int colorNo) {
        this.colorNo = colorNo;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventKind() {
        return eventKind;
    }

    public void setEventKind(int eventKind) {
        this.eventKind = eventKind;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getBossCellNo() {
        return bossCellNo;
    }

    public void setBossCellNo(int bossCellNo) {
        this.bossCellNo = bossCellNo;
    }

    public int getBossComp() {
        return bossComp;
    }

    public void setBossComp(int bossComp) {
        this.bossComp = bossComp;
    }

    public AirSearch getAirSearch() {
        return airSearch;
    }

    public void setAirSearch(AirSearch airSearch) {
        this.airSearch = airSearch;
    }

    public int getRationFlag() {
        return rationFlag;
    }

    public void setRationFlag(int rationFlag) {
        this.rationFlag = rationFlag;
    }

    public MapEventMap getEventMap() {
        return eventMap;
    }

    public void setEventMap(MapEventMap eventMap) {
        this.eventMap = eventMap;
    }

    public MapItemGet getItemGet() {
        return itemGet;
    }

    public void setItemGet(MapItemGet itemGet) {
        this.itemGet = itemGet;
    }
}
