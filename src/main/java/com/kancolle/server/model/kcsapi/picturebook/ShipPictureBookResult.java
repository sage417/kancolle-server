package com.kancolle.server.model.kcsapi.picturebook;

import com.kancolle.server.model.po.picturebook.ShipPictureBook;

import java.util.List;

public class ShipPictureBookResult {

    private List<ShipPictureBook> api_list;

    public ShipPictureBookResult(List<ShipPictureBook> api_list) {
        this.api_list = api_list;
    }

    public List<ShipPictureBook> getApi_list() {
        return api_list;
    }

    public void setApi_list(List<ShipPictureBook> api_list) {
        this.api_list = api_list;
    }
}
