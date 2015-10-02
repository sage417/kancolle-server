/**
 * 
 */
package com.kancolle.server.model.po.slotitem;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 *
 */
@Alias("MemberSlotItem")
public class MemberSlotItem extends AbstractSlotItem implements Serializable {

    private static final long serialVersionUID = -7169523354675835935L;

    @JSONField(serialize = false, deserialize = false)
    private String memberId;

    @JSONField(ordinal = 1, name = "api_id")
    private long memberSlotItemId;

    @JSONField(ordinal = 2, name = "api_slotitem_id")
    public int returnSlotItemId() {
        return getSlotItem().getSlotItemId();
    }

    @JSONField(ordinal = 3, name = "api_locked")
    private boolean locked;

    @JSONField(ordinal = 4, name = "api_level")
    private int level;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public long getMemberSlotItemId() {
        return memberSlotItemId;
    }

    public void setMemberSlotItemId(long memberSlotItemId) {
        this.memberSlotItemId = memberSlotItemId;
    }

    public boolean getLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
        result = prime * result + (int) (memberSlotItemId ^ (memberSlotItemId >>> 32));
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
        MemberSlotItem other = (MemberSlotItem) obj;
        if (memberId == null) {
            if (other.memberId != null)
                return false;
        } else if (!memberId.equals(other.memberId))
            return false;
        if (memberSlotItemId != other.memberSlotItemId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MemberSlotItem [memberId=%s, memberSlotItemId=%s, slotItem=%s, level=%s]", memberId, memberSlotItemId, getSlotItem(), level);
    }
}
