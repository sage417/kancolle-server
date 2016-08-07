/**
 *
 */
package com.kancolle.server.model.kcsapi.charge;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.Lists;
import com.kancolle.server.model.po.resource.Resource;
import com.kancolle.server.model.po.ship.MemberShip;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 */
@JsonPropertyOrder(value = {
        "api_ship", "api_material", "api_use_bou"
})
public class ChargeModel {

    @JsonProperty(value = "api_ship")
    @JSONField(ordinal = 1)
    private List<ShipChargeModel> api_ship;

    @JsonProperty(value = "api_material")
    @JSONField(ordinal = 2)
    private int[] api_material;

    @JsonProperty(value = "api_use_bou")
    @JSONField(ordinal = 3)
    private int api_use_bou;

    /**
     * @param memberShips
     * @param rescource
     */
    public ChargeModel(List<MemberShip> memberShips, Resource rescource, boolean useBauxite) {
        this.api_ship = Lists.newArrayListWithCapacity(memberShips.size());
        for (MemberShip memberShip : memberShips) {
            this.api_ship.add(new ShipChargeModel(memberShip));
        }
        this.api_material = new int[]{rescource.getFuel(), rescource.getBull(), rescource.getSteel(), rescource.getBauxite()};
        this.api_use_bou = useBauxite ? 1 : 0;
    }

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
