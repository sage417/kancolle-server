/**
 * 
 */
package com.kancolle.server.model.po.furniture;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年7月27日
 *
 */
@Alias("BGM")
public class BGM implements Serializable {

    private static final long serialVersionUID = 2706276442054216220L;

    @JSONField(ordinal = 1, name = "api_id")
    private int index;

    @JSONField(ordinal = 2, name = "api_name")
    private String name;

    @JSONField(ordinal = 3, name = "api_description")
    private String description;

    @JSONField(ordinal = 4, name = "api_bgm_id")
    private int bgmId;

    @JSONField(ordinal = 5, name = "api_use_coin")
    private int useCoin;

    @JSONField(ordinal = 6, name = "api_bgm_flag")
    private int bgmFlag;

    @JSONField(ordinal = 7, name = "api_loops")
    private int loops;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
        result = prime * result + index;
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
        BGM other = (BGM) obj;
        if (index != other.index)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("BGM [name=%s]", name);
    }
}
