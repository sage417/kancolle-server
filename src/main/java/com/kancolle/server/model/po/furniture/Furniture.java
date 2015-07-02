/**
 * 
 */
package com.kancolle.server.model.po.furniture;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年6月10日
 *
 */
@Alias("Furniture")
public class Furniture {

    @JSONField(ordinal = 1, name = "api_id")
    private int furnitureId;

    @JSONField(ordinal = 2, name = "api_type")
    private int type;

    @JSONField(ordinal = 3, name = "api_no")
    private int no;

    @JSONField(ordinal = 4, name = "api_title")
    private String title;

    @JSONField(ordinal = 5, name = "api_description")
    private String description;

    @JSONField(ordinal = 6, name = "api_rarity")
    private int rarity;

    @JSONField(ordinal = 7, name = "api_price")
    private int price;

    @JSONField(serialize = false, deserialize = false)
    private boolean saleFalg;

    @JSONField(ordinal = 8, name = "api_saleflg")
    public int getSaleState() {
        return saleFalg ? 1 : 0;
    }

    @JSONField(ordinal = 9, name = "api_season")
    private int season;

    public int getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(int furnitureId) {
        this.furnitureId = furnitureId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSaleFalg() {
        return saleFalg;
    }

    public void setSaleFalg(boolean saleFalg) {
        this.saleFalg = saleFalg;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + furnitureId;
        result = prime * result + no;
        result = prime * result + price;
        result = prime * result + rarity;
        result = prime * result + (saleFalg ? 1231 : 1237);
        result = prime * result + season;
        result = prime * result + type;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Furniture other = (Furniture) obj;
        if (furnitureId != other.furnitureId)
            return false;
        if (no != other.no)
            return false;
        if (price != other.price)
            return false;
        if (rarity != other.rarity)
            return false;
        if (saleFalg != other.saleFalg)
            return false;
        if (season != other.season)
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Furniture [title=%s]", title);
    }
}
