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
@Alias("BaseBGM")
public class BaseBGM implements Serializable {

    private static final long serialVersionUID = -6386444954987433645L;

    @JSONField(ordinal = 1, name = "api_id")
    private int bgmId;

    @JSONField(ordinal = 2, name = "api_name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBgmId() {
        return bgmId;
    }

    public void setBgmId(int bgmId) {
        this.bgmId = bgmId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + bgmId;
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
        BaseBGM other = (BaseBGM) obj;
        if (bgmId != other.bgmId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("BaseBGM [name=%s]", name);
    }
}
