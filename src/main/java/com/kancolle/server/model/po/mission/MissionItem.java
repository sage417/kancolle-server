/**
 * 
 */
package com.kancolle.server.model.po.mission;

import java.io.Serializable;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 *
 */
public class MissionItem implements Serializable {

    private static final long serialVersionUID = -5021007938921203343L;

    private int useItemId;

    private int itemCount;

    public MissionItem(int useItemId, int itemCount) {
        super();
        this.useItemId = useItemId;
        this.itemCount = itemCount;
    }

    public int getUseItemId() {
        return useItemId;
    }

    public void setUseItemId(int useItemId) {
        this.useItemId = useItemId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + itemCount;
        result = prime * result + useItemId;
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
        MissionItem other = (MissionItem) obj;
        if (itemCount != other.itemCount)
            return false;
        if (useItemId != other.useItemId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("MissionItem [useItemId=%s, itemCount=%s]", useItemId, itemCount);
    }

    public int[] toArray() {
        return new int[] { useItemId, itemCount };
    }
}
