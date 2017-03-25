/**
 *
 */
package com.kancolle.server.model.po.furniture;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author J.K.SAGE
 * @Date 2015年7月27日
 */
@JsonPropertyOrder(value = {
        "id", "name", "description", "bgmId",
        "useCoin", "bgmFlag", "loops",
})
@Alias("FurnitureBGM")
public class FurnitureBGM implements Serializable {

    private static final long serialVersionUID = 2706276442054216220L;

    @JsonProperty(value = "api_id")
    @JSONField(ordinal = 1, name = "api_id")
    private int id;

    @JsonProperty(value = "api_name")
    @JSONField(ordinal = 2, name = "api_name")
    private String name;

    @JsonProperty(value = "api_description")
    @JSONField(ordinal = 3, name = "api_description")
    private String description;

    @JsonProperty(value = "api_bgm_id")
    @JSONField(ordinal = 4, name = "api_bgm_id")
    private int bgmId;

    @JsonProperty(value = "api_use_coin")
    @JSONField(ordinal = 5, name = "api_use_coin")
    private int useCoin;

    @JsonProperty(value = "api_bgm_flag")
    @JSONField(ordinal = 6, name = "api_bgm_flag")
    private int bgmFlag;

    @JsonProperty(value = "api_loops")
    @JSONField(ordinal = 7, name = "api_loops")
    private int loops;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBgmId() {
        return bgmId;
    }

    public void setBgmId(int bgmId) {
        this.bgmId = bgmId;
    }

    public int getUseCoin() {
        return useCoin;
    }

    public void setUseCoin(int useCoin) {
        this.useCoin = useCoin;
    }

    public int getBgmFlag() {
        return bgmFlag;
    }

    public void setBgmFlag(int bgmFlag) {
        this.bgmFlag = bgmFlag;
    }

    public int getLoops() {
        return loops;
    }

    public void setLoops(int loops) {
        this.loops = loops;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        FurnitureBGM other = (FurnitureBGM) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return String.format("BGM [name=%s]", name);
    }
}
