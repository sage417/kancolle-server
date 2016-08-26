/**
 *
 */
package com.kancolle.server.model.po.picturebook;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kancolle.server.model.po.ship.Ship;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年6月22日
 */
@JsonPropertyOrder(value = {
        "api_index_no", "api_state", "api_table_id", "api_name",
        "api_yomi", "api_stype", "api_ctype", "api_cnum",
        "api_taik", "api_souk", "api_kaih", "api_houg",
        "api_raig", "api_tyku", "api_tais", "api_leng",
        "api_sinfo"
})
@Alias("ShipPictureBook")
public class ShipPictureBook implements Serializable {

    private static final long serialVersionUID = 2679813325131518778L;

    @JsonProperty(value = "api_index_no")
    @JSONField(ordinal = 1, name = "api_index_no")
    public int getShipNo() {
        return getShip().getSortno();
    }

    @JsonProperty(value = "api_state")
    @JSONField(ordinal = 2)
    private int[][] api_state = {{1, 1, 0, 0, 0}};

    @JsonProperty(value = "api_table_id")
    @JSONField(ordinal = 3, name = "api_table_id")
    private List<Integer> api_table_id;

    @JsonProperty(value = "api_name")
    @JSONField(ordinal = 4, name = "api_name")
    public String getShipName() {
        return getShip().getName();
    }

    @JsonProperty(value = "api_yomi")
    @JSONField(ordinal = 5, name = "api_yomi")
    public String getShipYomi() {
        return getShip().getYomi();
    }

    @JsonProperty(value = "api_stype")
    @JSONField(ordinal = 6, name = "api_stype")
    public int getShipType() {
        return getShip().getShipTypeId();
    }

    @JsonProperty(value = "api_ctype")
    @JSONField(ordinal = 7, name = "api_ctype")
    private int ctype;

    @JsonProperty(value = "api_cnum")
    @JSONField(ordinal = 8, name = "api_cnum")
    private int cnum;

    @JsonProperty(value = "api_taik")
    @JSONField(ordinal = 9, name = "api_taik")
    public int getShipTaik() {
        return getShip().getTaik().getMinValue();
    }

    @JsonProperty(value = "api_souk")
    @JSONField(ordinal = 10, name = "api_souk")
    public int getShipSouk() {
        return getShip().getSouk().getMinValue();
    }

    @JsonProperty(value = "api_kaih")
    @JSONField(ordinal = 11, name = "api_kaih")
    public int getShipKaih() {
        return getShip().getKaihi().getMinValue();
    }

    @JsonProperty(value = "api_houg")
    @JSONField(ordinal = 12, name = "api_houg")
    public int getShipHoug() {
        return getShip().getHoug().getMinValue();
    }

    @JsonProperty(value = "api_raig")
    @JSONField(ordinal = 13, name = "api_raig")
    public int getShipRaig() {
        return getShip().getRaig().getMinValue();
    }

    @JsonProperty(value = "api_tyku")
    @JSONField(ordinal = 14, name = "api_tyku")
    public int getShipTyku() {
        return getShip().getTyku().getMinValue();
    }

    @JsonProperty(value = "api_tais")
    @JSONField(ordinal = 15, name = "api_tais")
    public int getShipTais() {
        return getShip().getTaisen().getMinValue();
    }

    @JsonProperty(value = "api_leng")
    @JSONField(ordinal = 16, name = "api_leng")
    public int getShipLeng() {
        return getShip().getLeng();
    }

    @JsonProperty(value = "api_leng")
    @JSONField(ordinal = 17, name = "api_sinfo")
    public String shipInfo;

    @JsonIgnore
    @JSONField(serialize = false, deserialize = false)
    private Ship ship;

    public int[][] getApi_state() {
        return api_state;
    }

    public void setApi_state(int[][] api_state) {
        this.api_state = api_state;
    }

    public List<Integer> getApi_table_id() {
        return api_table_id;
    }

    public void setApi_table_id(List<Integer> api_table_id) {
        this.api_table_id = api_table_id;
    }

    public int getCtype() {
        return ctype;
    }

    public void setCtype(int ctype) {
        this.ctype = ctype;
    }

    public int getCnum() {
        return cnum;
    }

    public void setCnum(int cnum) {
        this.cnum = cnum;
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
