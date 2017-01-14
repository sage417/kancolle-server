package com.kancolle.server.model.kcsapi.battle.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kancolle.server.utils.jackson.NumericBooleanSerializer;

/**
 * Created by J.K.SAGE on 2017/1/14.
 */
@JsonPropertyOrder({
        "api_id", "api_no", "api_color_no", "api_passed"
})
public class MemberMapCellView {

    @JsonProperty("api_id")
    private int mapCellId;

    @JsonProperty("api_no")
    private int api_no;

    @JsonProperty("api_color_no")
    private int api_color_no;

    @JsonProperty("api_passed")
    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean passed;

    public int getMapCellId() {
        return mapCellId;
    }

    public void setMapCellId(int mapCellId) {
        this.mapCellId = mapCellId;
    }

    public int getApi_no() {
        return api_no;
    }

    public void setApi_no(int api_no) {
        this.api_no = api_no;
    }

    public int getApi_color_no() {
        return api_color_no;
    }

    public void setApi_color_no(int api_color_no) {
        this.api_color_no = api_color_no;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
