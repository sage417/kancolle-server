/**
 * 
 */
package com.kancolle.server.model.po.common;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
public class ResourceValue {

    private int fuel;

    private int bull;

    private int steal;

    private int baxuite;

    public ResourceValue() {
        super();
    }

    public ResourceValue(int fuel, int bull, int steal, int baxuite) {
        super();
        this.fuel = fuel;
        this.bull = bull;
        this.steal = steal;
        this.baxuite = baxuite;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getBull() {
        return bull;
    }

    public void setBull(int bull) {
        this.bull = bull;
    }

    public int getSteal() {
        return steal;
    }

    public void setSteal(int steal) {
        this.steal = steal;
    }

    public int getBaxuite() {
        return baxuite;
    }

    public void setBaxuite(int baxuite) {
        this.baxuite = baxuite;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + baxuite;
        result = prime * result + bull;
        result = prime * result + fuel;
        result = prime * result + steal;
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
        ResourceValue other = (ResourceValue) obj;
        if (baxuite != other.baxuite)
            return false;
        if (bull != other.bull)
            return false;
        if (fuel != other.fuel)
            return false;
        if (steal != other.steal)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s,%s,%s]", fuel, bull, steal, baxuite);
    }
}
