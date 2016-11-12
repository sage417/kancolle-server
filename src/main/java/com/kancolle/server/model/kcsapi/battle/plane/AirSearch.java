/**
 *
 */
package com.kancolle.server.model.kcsapi.battle.plane;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author J.K.SAGE
 * @Date 2015年8月20日
 */
@JsonPropertyOrder(value = {
        "api_plane_type", "api_result"
})
public class AirSearch {

    public static final AirSearch NO_AIR_SEARCH = new AirSearch(0, 0);

    @JsonProperty(value = "api_plane_type")
    private int api_plane_type;

    @JsonProperty(value = "api_result")
    private int api_result;

    public AirSearch(int api_plane_type, int api_result) {
        this.api_plane_type = api_plane_type;
        this.api_result = api_result;
    }

    public int getApi_plane_type() {
        return api_plane_type;
    }

    public void setApi_plane_type(int api_plane_type) {
        this.api_plane_type = api_plane_type;
    }

    public int getApi_result() {
        return api_result;
    }

    public void setApi_result(int api_result) {
        this.api_result = api_result;
    }
}
