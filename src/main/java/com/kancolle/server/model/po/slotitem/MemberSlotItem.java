/**
 * 
 */
package com.kancolle.server.model.po.slotitem;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 *
 */
@Alias("MemberSlotItem")
public class MemberSlotItem {

    @JSONField(ordinal = 1, name = "api_id")
    private long memberSlotItemId;

    @JSONField(ordinal = 2, name = "api_slotitem_id")
    private long slotItemId;

    @JSONField(serialize = false, deserialize = false)
    private SlotItem slotItem;

    @JSONField(ordinal = 3, name = "api_locked")
    private boolean locked;

    @JSONField(ordinal = 4, name = "api_level")
    private int level;

    public long getMemberSlotItemId() {
        return memberSlotItemId;
    }

    public void setMemberSlotItemId(long memberSlotItemId) {
        this.memberSlotItemId = memberSlotItemId;
    }

    public long getSlotItemId() {
        return slotItemId;
    }

    public void setSlotItemId(long slotItemId) {
        throw new UnsupportedOperationException();
    }

    public SlotItem getSlotItem() {
        return slotItem;
    }

    public void setSlotItem(SlotItem slotItem) {
        this.slotItem = slotItem;
        this.slotItemId = slotItem.getSlotItemId();
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
        if (memberSlotItemId != other.memberSlotItemId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MemberSlotItem [slotItem=%s, level=%s]", slotItem, level);
    }
}
