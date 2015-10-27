
/**
 * 
 */
package com.kancolle.server.model.po.ship;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.model.po.slotitem.EnemySlotItem;

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

  //------抽象方法------//
    
    @Override
    public List<? extends AbstractSlotItem> getSlotItems() {
        return getSlot();
    }

    @Override
    public int[] getCurrentEQ() {
        return Arrays.copyOf(getShip().getMaxEq(), getShip().getMaxEq().length);
    }

    @Override
    public int getLeng() {
        return getShip().getLeng();
    }

    @Override
    public int getNowSoukou() {
        return getShip().getSouk().getMinValue();
    }

    @Override
    public int getNowLuck() {
        return getShip().getLuck().getMinValue();
    }

    @Override
    public int getMaxHp() {
        return getShip().getTaik().getMaxValue();
    }

    @Override
    public int getShipTaiSen() {
        return getShip().getTaisen().getMinValue();
    }
}
