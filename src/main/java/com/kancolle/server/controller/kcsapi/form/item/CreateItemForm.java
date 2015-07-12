/**
 * 
 */
package com.kancolle.server.controller.kcsapi.form.item;

import javax.validation.constraints.Min;

/**
 * @author J.K.SAGE
 * @Date 2015年7月12日
 *
 */
public class CreateItemForm {

    @Min(value = 30L)
    private Integer api_item1;

    @Min(value = 30L)
    private Integer api_item2;

    @Min(value = 30L)
    private Integer api_item3;

    @Min(value = 30L)
    private Integer api_item4;

    public Integer getApi_item1() {
        return api_item1;
    }

    public void setApi_item1(Integer api_item1) {
        this.api_item1 = api_item1;
    }

    public Integer getApi_item2() {
        return api_item2;
    }

    public void setApi_item2(Integer api_item2) {
        this.api_item2 = api_item2;
    }

    public Integer getApi_item3() {
        return api_item3;
    }

    public void setApi_item3(Integer api_item3) {
        this.api_item3 = api_item3;
    }

    public Integer getApi_item4() {
        return api_item4;
    }

    public void setApi_item4(Integer api_item4) {
        this.api_item4 = api_item4;
    }
}
