
/**
 * 
 */
package com.kancolle.server.model.po.ship;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.type.Alias;

import com.kancolle.server.model.po.slotitem.EnemySlotItem;
import com.kancolle.server.model.po.slotitem.SlotItem;

/**
 * @author J.K.SAGE
 * @Date 2015年8月22日
 *
 */
@Alias("EnemyShip")
public class EnemyShip extends AbstractShip implements Serializable {

    private static final long serialVersionUID = 887568848561500767L;

    private int nowHp;

    private List<EnemySlotItem> slot;

    public int getNowHp() {
        return nowHp;
    }

    public void setNowHp(int nowHp) {
        this.nowHp = nowHp;
    }

    public List<EnemySlotItem> getSlot() {
        return slot;
    }

    public void setSlot(List<EnemySlotItem> slot) {
        this.slot = slot;
    }

    @Override
    public List<SlotItem> getSlotItems() {
        return getSlot().stream().map(EnemySlotItem::getSlotItem).collect(Collectors.toList());
    }

    @Override
    public int[] getCurrentEQ() {
        return Arrays.copyOf(getShip().getMaxEq(), getShip().getMaxEq().length);
    }

    @Override
    public int getLeng() {
        return getShip().getLeng();
    }
}
