package com.kancolle.server.model.po.duty;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("DutyBonus")
public class DutyBonus implements Serializable{

    private String dutyBonusId;

    private int type;

    private int itemId;

    private int count;

    public String getDutyBonusId() {
        return dutyBonusId;
    }

    public void setDutyBonusId(String dutyBonusId) {
        this.dutyBonusId = dutyBonusId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dutyBonusId == null) ? 0 : dutyBonusId.hashCode());
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
        DutyBonus other = (DutyBonus) obj;
        if (dutyBonusId == null) {
            if (other.dutyBonusId != null)
                return false;
        } else if (!dutyBonusId.equals(other.dutyBonusId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DutyBonus [type=" + type + ", itemId=" + itemId + ", count=" + count + "]";
    }
}
