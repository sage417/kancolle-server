/**
 *
 */
package com.kancolle.server.model.po.furniture;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kancolle.server.utils.jackson.NumericBooleanSerializer;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author J.K.SAGE
 * @Date 2015年6月10日
 */
@JsonPropertyOrder(value = {
        "furnitureId", "type", "no", "title",
        "description", "rarity", "price", "saleFlag",
        "season"
})
@Alias("Furniture")
public class Furniture implements Serializable {

    private static final long serialVersionUID = -6302293499953139928L;

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1, name = "api_id")
    private int furnitureId;

    @JsonProperty(value = "api_type")
    @JSONField(ordinal = 2, name = "api_type")
    private int type;

    @JsonProperty(value = "api_no")
    @JSONField(ordinal = 3, name = "api_no")
    private int no;

    @JsonProperty(value = "api_title")
    @JSONField(ordinal = 4, name = "api_title")
    private String title;

    @JsonProperty(value = "api_description")
    @JSONField(ordinal = 5, name = "api_description")
    private String description;

    @JsonProperty(value = "api_rarity")
    @JSONField(ordinal = 6, name = "api_rarity")
    private int rarity;

    @JsonProperty(value = "api_price")
    @JSONField(ordinal = 7, name = "api_price")
    private int price;

    @JsonProperty(value = "api_saleflg")
    @JsonSerialize(using = NumericBooleanSerializer.class)
    private boolean saleFlag;

    @JsonProperty(value = "api_season")
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

    public boolean isSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(boolean saleFlag) {
        this.saleFlag = saleFlag;
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
        result = prime * result + (saleFlag ? 1231 : 1237);
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
        if (saleFlag != other.saleFlag)
            return false;
        if (season != other.season)
            return false;
        return type == other.type;
    }

    @Override
    public String toString() {
        return String.format("Furniture [title=%s]", title);
    }
}
