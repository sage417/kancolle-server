package com.kancolle.server.controller.kcsapi.form.forniture;

import javax.validation.constraints.NotNull;

public class FurnitureBuyForm {

    @NotNull
    private Integer api_type;

    @NotNull
    private Integer api_no;

    public Integer getApi_type() {
        return api_type;
    }

    public void setApi_type(Integer api_type) {
        this.api_type = api_type;
    }

    public Integer getApi_no() {
        return api_no;
    }

    public void setApi_no(Integer api_no) {
        this.api_no = api_no;
    }

}
