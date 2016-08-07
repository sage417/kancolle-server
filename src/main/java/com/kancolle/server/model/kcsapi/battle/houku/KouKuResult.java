/**
 *
 */
package com.kancolle.server.model.kcsapi.battle.houku;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author J.K.SAGE
 * @Date 2015年11月14日
 */
@JsonPropertyOrder(value = {
        "api_plane_from", "stage1", "stage2", "stage3"
})
public class KouKuResult {

    @JsonProperty(value = "api_plane_from")
    @JSONField(name = "api_plane_from", ordinal = 1)
    private int[][] api_plane_from;

    @JsonProperty(value = "api_stage1")
    @JSONField(name = "api_stage1", ordinal = 2)
    private KouKuStage1 stage1;

    @JsonProperty(value = "api_stage2")
    @JSONField(name = "api_stage2", ordinal = 3)
    private KouKuStage2 stage2;

    @JsonProperty(value = "stage3")
    @JSONField(name = "api_stage3", ordinal = 4)
    private KouKuStage3 stage3;

    public int[][] getApi_plane_from() {
        return api_plane_from;
    }

    public void setApi_plane_from(int[][] api_plane_from) {
        this.api_plane_from = api_plane_from;
    }

    public KouKuStage1 getStage1() {
        return stage1;
    }

    public void setStage1(KouKuStage1 stage1) {
        this.stage1 = stage1;
    }

    public KouKuStage2 getStage2() {
        return stage2;
    }

    public void setStage2(KouKuStage2 stage2) {
        this.stage2 = stage2;
    }

    public KouKuStage3 getStage3() {
        return stage3;
    }

    public void setStage3(KouKuStage3 stage3) {
        this.stage3 = stage3;
    }
}
