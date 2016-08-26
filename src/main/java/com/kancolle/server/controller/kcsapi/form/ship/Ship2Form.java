package com.kancolle.server.controller.kcsapi.form.ship;

import javax.validation.constraints.NotNull;

public class Ship2Form {

    @NotNull
    private Integer api_sort_key;

    @NotNull
    private Integer spi_sort_order;

    public Integer getApi_sort_key() {
        return api_sort_key;
    }

    public void setApi_sort_key(Integer api_sort_key) {
        this.api_sort_key = api_sort_key;
    }

    public Integer getSpi_sort_order() {
        return spi_sort_order;
    }

    public void setSpi_sort_order(Integer spi_sort_order) {
        this.spi_sort_order = spi_sort_order;
    }
}
