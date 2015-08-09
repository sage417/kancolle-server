/**
 * 
 */
package com.kancolle.server.model.po.ship;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 *
 */
@Alias("ShipPictureBook")
public class ShipPictureBook {

    @JSONField(ordinal = 1, name = "api_index_no")
    public int getShipNo() {
        return getShip().getSortno();
    }

    @JSONField(ordinal = 2)
    private int[][] api_state = { { 1, 0, 0, 0, 0 } };

    @JSONField(ordinal = 3, name = "api_table_id")
    public int[] getShipId() {
        return new int[] { getShip().getShipId() };
    }

    @JSONField(ordinal = 4, name = "api_name")
    public String getShipName() {
        return getShip().getName();
    }

    @JSONField(ordinal = 5, name = "api_yomi")
    public String getShipYomi() {
        return getShip().getYomi();
    }

    @JSONField(ordinal = 6, name = "api_stype")
    public int getShipType() {
        return getShip().getType().getShipTypeId();
    }

    @JSONField(ordinal = 7)
    private int api_ctype = 1;

    @JSONField(ordinal = 8)
    private int api_cnum = 1;

    @JSONField(ordinal = 9, name = "api_taik")
    public int getShipTaik() {
        return getShip().getTaik().getMinValue();
    }

    @JSONField(ordinal = 10, name = "api_souk")
    public int getShipSouk() {
        return getShip().getSouk().getMinValue();
    }

    @JSONField(ordinal = 11, name = "api_kaih")
    public int getShipKaih() {
        return getShip().getKaihi().getMinValue();
    }

    @JSONField(ordinal = 12, name = "api_houg")
    public int getShipHoug() {
        return getShip().getHoug().getMinValue();
    }

    @JSONField(ordinal = 13, name = "api_raig")
    public int getShipRaig() {
        return getShip().getRaig().getMinValue();
    }

    @JSONField(ordinal = 14, name = "api_tyku")
    public int getShipTyku() {
        return getShip().getTyku().getMinValue();
    }

    @JSONField(ordinal = 15, name = "api_tais")
    public int getShipTais() {
        return getShip().getTaisen().getMinValue();
    }

    @JSONField(ordinal = 16, name = "api_leng")
    public int getShipLeng() {
        return getShip().getLeng();
    }

    @JSONField(ordinal = 17, name = "api_sinfo")
    public String shipInfo;

    @JSONField(serialize = false, deserialize = false)
    private Ship ship;

    public int[][] getApi_state() {
        return api_state;
    }

    public void setApi_state(int[][] api_state) {
        this.api_state = api_state;
    }

    public int getApi_ctype() {
        return api_ctype;
    }

    public void setApi_ctype(int api_ctype) {
        this.api_ctype = api_ctype;
    }

    public int getApi_cnum() {
        return api_cnum;
    }

    public void setApi_cnum(int api_cnum) {
        this.api_cnum = api_cnum;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public String getShipInfo() {
        return shipInfo;
    }

    public void setShipInfo(String shipInfo) {
        this.shipInfo = shipInfo;
    }
}
