/**
 * 
 */
package com.kancolle.server.model.po.common;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
public class MaxMinValue {

    private int minValue;

    private int MaxValue;

    public MaxMinValue() {
        super();
    }

    public MaxMinValue(int minValue, int maxValue) {
        super();
        this.minValue = minValue;
        MaxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return MaxValue;
    }

    public void setMaxValue(int maxValue) {
        MaxValue = maxValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + MaxValue;
        result = prime * result + minValue;
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
        MaxMinValue other = (MaxMinValue) obj;
        if (MaxValue != other.MaxValue)
            return false;
        if (minValue != other.minValue)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]", minValue, MaxValue);
    }

    public int[] toArray() {
        return new int[] { minValue, MaxValue };
    }
}
