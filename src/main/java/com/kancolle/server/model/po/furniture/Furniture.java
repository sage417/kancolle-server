/**
 * 
 */
package com.kancolle.server.model.po.furniture;

import org.apache.ibatis.type.Alias;

/**
 * @author J.K.SAGE
 * @Date 2015年6月10日
 *
 */
@Alias("Furniture")
public class Furniture {

    private int furnitureId;

    private int type;

    private int no;

    private int rarity;

    private int price;

    private boolean saleFalg;

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
        return String.format("Furniture [furnitureId=%s, type=%s, no=%s]", furnitureId, type, no);
    }
}
