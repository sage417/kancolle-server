/**
 * 
 */
package com.kancolle.server.model.po.map;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年8月19日
 *
 */
@Alias("MemberMapCell")
public class MemberMapCell implements Serializable {

    private static final long serialVersionUID = -3431432547408067452L;

    @JSONField(serialize = false, deserialize = false)
    private String memberId;

    @JSONField(ordinal = 1, name = "api_id")
    private int mapCellId;

    @JSONField(serialize = false, deserialize = false)
    private boolean passFlag;

    @JSONField(ordinal = 2, name = "api_passed")
    public int returnPassFlag() {
        return passFlag ? 1 : 0;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getMapCellId() {
        return mapCellId;
    }

    public void setMapCellId(int mapCellId) {
        this.mapCellId = mapCellId;
    }

    public boolean isPassFlag() {
        return passFlag;
    }

    public void setPassFlag(boolean passFlag) {
        this.passFlag = passFlag;
    }
}
