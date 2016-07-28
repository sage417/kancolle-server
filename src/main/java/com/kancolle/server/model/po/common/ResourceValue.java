/**
 *
 */
package com.kancolle.server.model.po.common;

import java.io.Serializable;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 */
public class ResourceValue implements Serializable {

    private static final long serialVersionUID = -8819379300218819433L;

    private final int fuel;

    private final int bull;

    private final int steel;

    private final int baxuite;

    public ResourceValue(final int fuel, final int bull, final int steel, final int baxuite) {
        this.fuel = fuel;
        this.bull = bull;
        this.steel = steel;
        this.baxuite = baxuite;
    }

    public int getFuel() {
        return fuel;
    }

    public int getBull() {
        return bull;
    }

    public int getSteel() {
        return steel;
    }

    public int getBaxuite() {
        return baxuite;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + baxuite;
        result = prime * result + bull;
        result = prime * result + fuel;
        result = prime * result + steel;
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
        if (steel != other.steel)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s,%s,%s]", fuel, bull, steel, baxuite);
    }

    public int[] toArray() {
        return new int[]{fuel, bull, steel, baxuite};
    }
}
