/**
 *
 */
package com.kancolle.server.model.po.map;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kancolle.server.utils.jackson.NumericBooleanSerializer;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author J.K.SAGE
 * @Date 2015年8月19日
 */
@JsonPropertyOrder(value = {
        "mapCellId", "passed"
})
@Alias("MemberMapCell")
public class MemberMapCell implements Serializable {

    private static final long serialVersionUID = -3431432547408067452L;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private String memberId;

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1, name = "api_id")
    private int mapCellId;

    @JsonProperty(value = "api_passed")
    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean passed;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getMapCellId() {
        return mapCellId;
    }

    public void setMapCellId(int mapCellId) {
        this.mapCellId = mapCellId;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
