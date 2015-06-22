/**
 * 
 */
package com.kancolle.server.model.kcsapi.charge;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
public class ChargeModel {

    @JSONField(ordinal = 1)
    private List<ShipChargeModel> api_ship;

    @JSONField(ordinal = 2)
    private int[] api_material;

    @JSONField(ordinal = 3)
    private int api_use_bou = 0;

    public List<ShipChargeModel> getApi_ship() {
        return api_ship;
    }

    public void setApi_ship(List<ShipChargeModel> api_ship) {
        this.api_ship = api_ship;
    }

    public int[] getApi_material() {
        return api_material;
    }

    public void setApi_material(int[] api_material) {
        this.api_material = api_material;
    }

    public int getApi_use_bou() {
        return api_use_bou;
    }

    public void setApi_use_bou(int api_use_bou) {
        this.api_use_bou = api_use_bou;
    }
}
