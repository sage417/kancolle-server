
/**
 *
 */
package com.kancolle.server.model.po.ship;

import com.kancolle.server.model.po.slotitem.EnemySlotItem;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
@Alias("EnemyShipTemplate")
public class EnemyShipTemplate implements Serializable {

    private List<EnemySlotItem> slot;

    public List<EnemySlotItem> getSlot() {
        return slot;
    }

    public void setSlot(List<EnemySlotItem> slot) {
        this.slot = slot;
    }
}
