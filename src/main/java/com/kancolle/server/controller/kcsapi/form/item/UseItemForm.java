/**
 * 
 */
package com.kancolle.server.controller.kcsapi.form.item;

import javax.validation.constraints.NotNull;

/**
 * @author J.K.SAGE
 * @Date 2015年6月11日
 *
 */
public class UseItemForm {

    @NotNull
    private Integer api_force_flag;

    @NotNull
    private Integer api_useitem_id;

    public Integer getApi_force_flag() {
        return api_force_flag;
    }

    public void setApi_force_flag(Integer api_force_flag) {
        this.api_force_flag = api_force_flag;
    }

    public Integer getApi_useitem_id() {
        return api_useitem_id;
    }

    public void setApi_useitem_id(Integer api_useitem_id) {
        this.api_useitem_id = api_useitem_id;
    }
}
