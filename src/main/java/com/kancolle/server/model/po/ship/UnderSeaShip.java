package com.kancolle.server.model.po.ship;

import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.model.po.slotitem.UnderSeaSlotItem;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by J.K.SAGE on 2016/3/29 0029.
 */
@Alias("UnderSeaShip")
public class UnderSeaShip implements IShip, Serializable {

    private Ship ship;

    private List<UnderSeaSlotItem> slot;

    private int nowHp;

    private int[] currentEQ;

    public UnderSeaShip() {
    }

    @Override
    public Ship getShip() {
        return this.ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
        this.nowHp = this.ship.getTaik().getMaxValue();
        int[] currentEQ = this.ship.getMaxEq();
        this.currentEQ = Arrays.copyOf(currentEQ, currentEQ.length);
    }

    public List<UnderSeaSlotItem> getSlot() {
        return slot;
    }

    public void setSlot(List<UnderSeaSlotItem> slot) {
        this.slot = slot;
    }

    @Override
    public int[] getCurrentEQ() {
        return this.currentEQ;
    }

    public void setCurrentEQ(int[] currentEQ) {
        this.currentEQ = currentEQ;
    }

    @Override
    public int getNowHp() {
        return this.nowHp;
    }

    public void setNowHp(int nowHp) {
        this.nowHp = nowHp;
    }

    @Override
    public List<? extends AbstractSlotItem> getSlotItems() {
        return getSlot();
    }

    @Override
    public int getLeng() {
        return getShip().getLeng();
    }

    @Override
    public int getShipSoukou() {
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

    @Override
    public int getShipKaihi() {
        return getShip().getKaihi().getMinValue();
    }

    @Override
    public int getShipKaryoku() {
        return getShip().getHoug().getMinValue();
    }

    @Override
    public int getShipRaisou() {
        return getShip().getRaig().getMinValue();
    }

    @Override
    public int getShipSakuteki() {
        return getShip().getSakuteki().getMinValue();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnderSeaShip{");
        sb.append("currentEQ=").append(Arrays.toString(currentEQ));
        sb.append(", ship=").append(ship);
        sb.append(", slot=").append(slot);
        sb.append(", nowHp=").append(nowHp);
        sb.append('}');
        return sb.toString();
    }
}
