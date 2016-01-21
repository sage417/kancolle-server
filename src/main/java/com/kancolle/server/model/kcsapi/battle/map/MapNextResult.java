package com.kancolle.server.model.kcsapi.battle.map;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by J.K.SAGE on 2016/1/17.
 */
@JsonPropertyOrder({
        "api_rashin_flg","api_rashin_id","api_maparea_id","api_mapinfo_no",
        "api_no","api_color_no","api_event_id","api_event_kind","api_next",
        "api_bosscell_no","api_bosscomp","api_comment_kind","api_production_kind","api_eventmap",
        "api_itemget","api_happening","api_get_eo_rate","api_itemget_eo_result","api_itemget_eo_comment",
        "api_select_route","api_airsearch"
})
public class MapNextResult extends MapStartResult {

    @JsonProperty("api_comment_kind")
    @JSONField(name="api_comment_kind",ordinal = 99)
    private int commentKind;

    @JSONField(name="api_production_kind",ordinal = 100)
    @JsonProperty("api_production_kind")
    private int productionKind;

    public int getCommentKind() {
        return commentKind;
    }

    public void setCommentKind(int commentKind) {
        this.commentKind = commentKind;
    }

    public int getProductionKind() {
        return productionKind;
    }

    public void setProductionKind(int productionKind) {
        this.productionKind = productionKind;
    }
}
