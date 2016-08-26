/**
 * 
 */
package com.kancolle.server.model.kcsapi.useitem;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.kcsapi.useitem.item.GetItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 *
 */
@JsonPropertyOrder({"cautionFlag","flag","getItem"})
public class UseItemResult {

    @JsonProperty("api_caution_flag")
    @JSONField(name="api_caution_flag",ordinal = 1)
    private int cautionFlag;

    @JsonProperty("api_flag")
    @JSONField(name="api_flag",ordinal = 2)
    private int flag;

    @JsonProperty("api_getitem")
    @JSONField(name="api_getitem",ordinal = 3)
    private GetItem getItem;

    public UseItemResult() {
    }

    public UseItemResult(int cautionFlag, int flag) {
        this.cautionFlag = cautionFlag;
        this.flag = flag;
    }

    public int getCautionFlag() {
        return cautionFlag;
    }

    public void setCautionFlag(int cautionFlag) {
        this.cautionFlag = cautionFlag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public GetItem getGetItem() {
        return getItem;
    }

    public void setGetItem(GetItem getItem) {
        this.getItem = getItem;
    }
}
