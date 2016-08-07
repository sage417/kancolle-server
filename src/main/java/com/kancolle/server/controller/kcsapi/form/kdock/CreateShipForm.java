package com.kancolle.server.controller.kcsapi.form.kdock;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

public class CreateShipForm {
    
    @Range(min = 1L, max = 4L)
    private Integer api_kdock_id;
    
    @Range(min = 30L, max = 7000L)
    private Integer api_item1;

    @Range(min = 30L, max = 7000L)
    private Integer api_item2;

    @Range(min = 30L, max = 7000L)
    private Integer api_item3;

    @Range(min = 30L, max = 7000L)
    private Integer api_item4;
    
    @Range(min = 1L, max = 100L)
    private Integer api_item5;

    @NotNull
    private Boolean api_highspeed;

    @NotNull
    private Boolean api_large_flag;

    public Integer getApi_kdock_id() {
        return api_kdock_id;
    }

    public void setApi_kdock_id(Integer api_kdock_id) {
        this.api_kdock_id = api_kdock_id;
    }

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

    public Integer getApi_item5() {
        return api_item5;
    }

    public void setApi_item5(Integer api_item5) {
        this.api_item5 = api_item5;
    }

    public Boolean getApi_highspeed() {
        return api_highspeed;
    }

    public void setApi_highspeed(Boolean api_highspeed) {
        this.api_highspeed = api_highspeed;
    }

    public Boolean getApi_large_flag() {
        return api_large_flag;
    }

    public void setApi_large_flag(Boolean api_large_flag) {
        this.api_large_flag = api_large_flag;
    }
}
