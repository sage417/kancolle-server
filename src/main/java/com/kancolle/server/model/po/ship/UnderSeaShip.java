package com.kancolle.server.model.po.ship;

import com.kancolle.server.model.po.slotitem.AbstractSlotItem;
import com.kancolle.server.model.po.slotitem.UnderSeaSlotItem;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by J.K.SAGE on 2016/3/29 0029.
 */
@Alias("UnderSeaShip")
public class UnderSeaShip implements IShip, Serializable {

    private final Ship ship;

    private final List<UnderSeaSlotItem> slot;

    private int nowHp;

    private int[] currentEQ;

    public UnderSeaShip(final Ship ship, final List<UnderSeaSlotItem> slot) {
        Objects.requireNonNull(ship);
        Objects.requireNonNull(slot);
        this.ship = ship;
        this.slot = slot;

        this.nowHp = this.ship.getTaik().getMaxValue();
        int[] currentEQ = this.ship.getMaxEq();
        this.currentEQ = Arrays.copyOf(currentEQ, currentEQ.length);
    }

    @Override
    public Ship getShip() {
        return this.ship;
    }

    public List<UnderSeaSlotItem> getSlot() {
        return slot;
    }

    @Override
    public int[] getCurrentEQ() {
        return Arrays.copyOf(this.currentEQ, this.currentEQ.length);
    }

    public void setCurrentEQ(int[] currentEQ) {
        this.currentEQ = Arrays.copyOf(currentEQ, currentEQ.length);
    }

    @Override
    public int getNowHp() {
        return this.nowHp;
    }

    @Override
    public void setNowHp(int nowHp) {
        this.nowHp = nowHp;
    }

    @Override
    public List<? extends AbstractSlotItem> getSlotItems() {
        return getSlot();
    }

    @Override
    public int getLeng() {
        return this.ship.getLeng();
    }

    @Override
    public int getShipSoukou() {
        return this.ship.getSouk().getMinValue();
    }

    public int getNowLv() {
        return this.ship.getLv();
    }

    @Override
    public int getNowLuck() {
        return this.ship.getLuck().getMinValue();
    }

    @Override
    public int getMaxHp() {
        return this.ship.getTaik().getMaxValue();
    }

    @Override
    public int getShipTaiSen() {
        return this.ship.getTaisen().getMinValue();
    }

    @Override
    public int getShipKaihi() {
        return this.ship.getKaihi().getMinValue();
    }

    @Override
    public int getShipKaryoku() {
        return this.ship.getHoug().getMinValue();
    }

    @Override
    public int getShipRaisou() {
        return this.ship.getRaig().getMinValue();
    }

    @Override
    public int getShipTaiku() {
        return this.ship.getTaik().getMinValue();
    }

    @Override
    public int getShipSakuteki() {
        return this.ship.getSakuteki().getMinValue();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnderSeaShip{");
        sb.append("ship=").append(ship);
        sb.append(", slot=").append(slot);
        sb.append(", nowHp=").append(nowHp);
        sb.append(", currentEQ=").append(Arrays.toString(currentEQ));
        sb.append('}');
        return sb.toString();
    }
}
