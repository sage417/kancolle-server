/**
 * 
 */
package com.kancolle.server.model.po.map;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
@Alias("MapArea")
public class MapArea implements Serializable {

    private static final long serialVersionUID = 6925572948954757048L;

    @JSONField(ordinal = 1, name = "api_id")
    private int mapareaId;

    @JSONField(ordinal = 2, name = "api_name")
    private String name;

    @JSONField(ordinal = 3, name = "api_type")
    private int type;

    public int getMapareaId() {
        return mapareaId;
    }

    public void setMapareaId(int mapareaId) {
        this.mapareaId = mapareaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + mapareaId;
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
        MapArea other = (MapArea) obj;
        if (mapareaId != other.mapareaId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MapArea [mapareaId=%s, name=%s, type=%s]", mapareaId, name, type);
    }
}
