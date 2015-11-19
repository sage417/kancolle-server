/**
 * 
 */
package com.kancolle.server.model.po.ship;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.kancolle.server.model.po.slotitem.AbstractSlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年10月2日
 *
 */
public abstract class AbstractShip {

    @JSONField(serialize = false, deserialize = false)
    private Ship ship;

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public abstract List<? extends AbstractSlotItem> getSlotItems();

    public abstract int[] getCurrentEQ();

    public abstract int getLeng();

    public abstract int getNowLuck();

    public abstract int getNowHp();

    public abstract int getMaxHp();

    /** 火力 */
    public abstract int getShipKaryoku();

    /** 装甲 */
    public abstract int getNowSoukou();

    /** 对潜 */
    public abstract int getShipTaiSen();

    /** 回避 */
    public abstract int getShipKaihi();

    public abstract int getShipSakuteki();

    public final int getShipDefendValue() {
        int rdmValue = RandomUtils.nextInt(2, 5);
        return rdmValue * getNowSoukou() / 3;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ship == null) ? 0 : getShip().hashCode());
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
        AbstractShip other = (AbstractShip) obj;
        if (ship == null) {
            if (other.ship != null)
                return false;
        } else if (!getShip().equals(other.ship))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("ParentShip [ship=%s]", getShip());
    }
}
