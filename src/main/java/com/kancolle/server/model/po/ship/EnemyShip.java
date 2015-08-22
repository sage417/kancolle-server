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
public class EnemyShip extends Ship {

    private static final long serialVersionUID = 887568848561500767L;

    private List<SlotItem> slot;

    public List<SlotItem> getSlot() {
        return slot;
    }

    public void setSlot(List<SlotItem> slot) {
        this.slot = slot;
    }
}
