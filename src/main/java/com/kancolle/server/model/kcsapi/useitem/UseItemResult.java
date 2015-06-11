/**
 * 
 */
package com.kancolle.server.model.kcsapi.useitem;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.kcsapi.useitem.item.GetItem;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 *
 */
public class UseItemResult {

    @JSONField(ordinal = 1)
    private int api_caution_flag;

    @JSONField(ordinal = 2)
    private int api_flag;

    @JSONField(ordinal = 3)
    private GetItem api_getitem;

    public UseItemResult() {
    }

    public UseItemResult(int api_caution_flag, int api_flag) {
        this.api_caution_flag = api_caution_flag;
        this.api_flag = api_flag;
    }

    public int getApi_caution_flag() {
        return api_caution_flag;
    }

    public void setApi_caution_flag(int api_caution_flag) {
        this.api_caution_flag = api_caution_flag;
    }

    public int getApi_flag() {
        return api_flag;
    }

    public void setApi_flag(int api_flag) {
        this.api_flag = api_flag;
    }

    public GetItem getApi_getitem() {
        return api_getitem;
    }

    public void setApi_getitem(GetItem api_getitem) {
        this.api_getitem = api_getitem;
    }
}
