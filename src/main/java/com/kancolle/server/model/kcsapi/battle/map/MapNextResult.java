package com.kancolle.server.model.kcsapi.battle.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.map.MapCellNext;

/**
 * Created by J.K.SAGE on 2016/1/17.
 */
@JsonPropertyOrder({
        "api_rashin_flg", "api_rashin_id", "api_maparea_id", "api_mapinfo_no",
        "api_no", "api_color_no", "api_event_id", "api_event_kind",
        "api_next", "api_bosscell_no", "api_bosscomp", "api_airsearch",
        "api_ration_flag", "api_comment_kind", "api_production_kind", "api_itemget"
})
public class MapNextResult extends MapStartResult {

    @JsonProperty("api_comment_kind")
    private int commentKind;

    @JsonProperty("api_production_kind")
    private int productionKind;

    public MapNextResult(final MapCellNext next) {
        super(next);
    }

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
