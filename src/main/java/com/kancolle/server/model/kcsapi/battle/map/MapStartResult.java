/**
 *
 */
package com.kancolle.server.model.kcsapi.battle.map;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.kcsapi.battle.plane.AirSearch;

/**
 * @author J.K.SAGE
 * @Date 2015年8月20日
 */
@JsonPropertyOrder({
        "api_rashin_flg","api_rashin_id","api_maparea_id","api_mapinfo_no",
        "api_no","api_color_no","api_event_id","api_event_kind","api_next",
        "api_bosscell_no","api_bosscomp","api_eventmap",
        "api_itemget","api_happening","api_get_eo_rate","api_itemget_eo_result","api_itemget_eo_comment",
        "api_select_route","api_airsearch"
})
public class MapStartResult {

    @JSONField(ordinal = 1, name = "api_rashin_flg")
    @JsonProperty("api_rashin_flg")
    private int rashinFlag;

    @JSONField(ordinal = 2, name = "api_rashin_id")
    @JsonProperty("api_rashin_id")
    private int rashinId;

    @JSONField(ordinal = 3, name = "api_maparea_id")
    @JsonProperty("api_maparea_id")
    private int mapareaId;

    @JSONField(ordinal = 4, name = "api_mapinfo_no")
    @JsonProperty("api_mapinfo_no")
    private int mapinfoId;

    @JSONField(ordinal = 5, name = "api_no")
    @JsonProperty("api_no")
    private int no;

    @JSONField(ordinal = 6, name = "api_color_no")
    @JsonProperty("api_color_no")
    private int colorNo;

    @JSONField(ordinal = 7, name = "api_event_id")
    @JsonProperty("api_event_id")
    private int eventId;

    @JSONField(ordinal = 8, name = "api_event_kind")
    @JsonProperty("api_event_kind")
    private int eventKind;

    @JSONField(ordinal = 9, name = "api_next")
    @JsonProperty("api_next")
    private int next;

    @JSONField(ordinal = 10, name = "api_bosscell_no")
    @JsonProperty("api_bosscell_no")
    private int bossCellNo;

    @JSONField(ordinal = 11, name = "api_bosscomp")
    @JsonProperty("api_bosscomp")
    private int bossComp;

    @JSONField(ordinal = 12, name = "api_airsearch")
    @JsonProperty("api_airsearch")
    private AirSearch airSearch = AirSearch.NO_AIRSEARCH;

    @JSONField(ordinal = 13, name = "api_itemget")
    @JsonProperty("api_itemget")
    private MapItemGet itemGet;

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

    public MapItemGet getItemGet() {
        return itemGet;
    }

    public void setItemGet(MapItemGet itemGet) {
        this.itemGet = itemGet;
    }
}
