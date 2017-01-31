/**
 *
 */
package com.kancolle.server.model.po.slotitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.ibatis.type.Alias;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Transient;

import java.io.Serializable;

/**
 * @author J.K.SAGE
 * @Date 2015年5月31日
 */
@Alias("MemberSlotItem")
@JsonPropertyOrder(value = {
        "memberSlotItemId", "slotItemId", "locked", "level"
})
public class MemberSlotItem extends AbstractSlotItem implements ISlotItem, Serializable {

    private static final long serialVersionUID = -7169523354675835935L;
    @Transient
    @JsonIgnore
    private String memberId;
    @Property("mid")
    @JsonProperty(value = "api_id")
    private long memberSlotItemId;
    @Property("id")
    @JsonProperty(value = "api_slotitem_id")
    private int slotItemId;
    @Transient
    @JsonProperty(value = "api_locked")
    private boolean locked;
    @Property("level")
    @JsonProperty(value = "api_level")
    private int level;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public int getSlotItemId() {
        return slotItemId;
    }

    public void setSlotItemId(int slotItemId) {
        this.slotItemId = slotItemId;
    }

    public long getMemberSlotItemId() {
        return memberSlotItemId;
    }

    public void setMemberSlotItemId(long memberSlotItemId) {
        this.memberSlotItemId = memberSlotItemId;
    }

    public boolean isLocked() {
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

    @Override
    public int getTaiSen() {
        return getSlotItem().getTais();
    }
}
