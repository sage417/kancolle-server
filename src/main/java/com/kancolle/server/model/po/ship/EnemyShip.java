/**
 * 
 */
package com.kancolle.server.model.po.ship;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kancolle.server.model.po.slotitem.SlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
@Alias("EnemyShip")
public class EnemyShip extends Ship implements AdapterShip {

    private static final long serialVersionUID = 887568848561500767L;

    private List<SlotItem> slot;

    public List<SlotItem> getSlot() {
        return slot;
    }

    public void setSlot(List<SlotItem> slot) {
        this.slot = slot;
    }

    @Override
    public List<SlotItem> getAdapterSlotItem() {
        return getSlot();
    }

    @Override
    public int[] getAdapterCurrentEQ() {
        return getMaxEq();
    }

    @Override
    public int getAdapterTypeId() {
        return getType().getShipTypeId();
    }

    @Override
    public int getAdapterLeng() {
        return getLeng();
    }
}
