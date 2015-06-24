/**
 * 
 */
package com.kancolle.server.model.po.ship;

/**
 * @author J.K.SAGE
 * @Date 2015年6月24日
 *
 */
public class ShipRepairMaterial {

    private int needFuel;

    private int needSteel;

    public int getNeedFuel() {
        return needFuel;
    }

    public void setNeedFuel(int needFuel) {
        this.needFuel = needFuel;
    }

    public int getNeedSteel() {
        return needSteel;
    }

    public void setNeedSteel(int needSteel) {
        this.needSteel = needSteel;
    }

    public int[] toArray() {
        return new int[] { needFuel, needSteel };
    }
}
