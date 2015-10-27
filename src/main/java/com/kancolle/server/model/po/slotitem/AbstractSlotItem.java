/**
 * 
 */
package com.kancolle.server.model.po.slotitem;

import com.kancolle.server.model.po.common.ResourceValue;

/**
 * @author J.K.SAGE
 * @Date 2015年10月2日
 *
 */
public abstract class AbstractSlotItem {

    private SlotItem slotItem;

    public SlotItem getSlotItem() {
        return slotItem;
    }

    public void setSlotItem(SlotItem slotItem) {
        this.slotItem = slotItem;
    }

    public abstract int getTaiSen();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSlotItem() == null) ? 0 : getSlotItem().hashCode());
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
        AbstractSlotItem other = (AbstractSlotItem) obj;
        if (getSlotItem() == null) {
            if (other.getSlotItem() != null)
                return false;
        } else if (!getSlotItem().equals(other.getSlotItem()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("AbstractSlotItem [slotItem=%s]", getSlotItem());
    }

    public int[] getBrokenArray() {
        return getSlotItem().getBrokenArray();
    }

    public int getSlotItemId() {
        return getSlotItem().getSlotItemId();
    }

    public int getSortno() {
        return getSlotItem().getSortno();
    }

    public String getName() {
        return getSlotItem().getName();
    }

    public int[] getType() {
        return getSlotItem().getType();
    }

    public int getTaik() {
        return getSlotItem().getTaik();
    }

    public int getSouk() {
        return getSlotItem().getSouk();
    }

    public int getHoug() {
        return getSlotItem().getHoug();
    }

    public int getRaig() {
        return getSlotItem().getRaig();
    }

    public int getSoku() {
        return getSlotItem().getSoku();
    }

    public int getBaku() {
        return getSlotItem().getBaku();
    }

    public int getTyku() {
        return getSlotItem().getTyku();
    }

    public int getTais() {
        return getSlotItem().getTais();
    }

    public int getAtap() {
        return getSlotItem().getAtap();
    }

    public int getHoum() {
        return getSlotItem().getHoum();
    }

    public int getRaim() {
        return getSlotItem().getRaim();
    }

    public int getHouk() {
        return getSlotItem().getHouk();
    }

    public int getRaik() {
        return getSlotItem().getRaik();
    }

    public int getBakk() {
        return getSlotItem().getBakk();
    }

    public int getSaku() {
        return getSlotItem().getSaku();
    }

    public int getSakb() {
        return getSlotItem().getSakb();
    }

    public int getLuck() {
        return getSlotItem().getLuck();
    }

    public int getLeng() {
        return getSlotItem().getLeng();
    }

    public int getRare() {
        return getSlotItem().getRare();
    }

    public ResourceValue getBroken() {
        return getSlotItem().getBroken();
    }

    public String getInfo() {
        return getSlotItem().getInfo();
    }

    public String getUseBull() {
        return getSlotItem().getUseBull();
    }

}
