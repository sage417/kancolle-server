/**
 * 
 */
package com.kancolle.server.model.po.common;

import java.io.Serializable;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 *
 */
public class MaxMinValue implements Serializable {

    private static final long serialVersionUID = 4788796975717664083L;

    private int minValue;

    private int maxValue;

    public MaxMinValue() {
        super();
    }

    public MaxMinValue(int minValue, int maxValue) {
        super();
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + maxValue;
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
        if (maxValue != other.maxValue)
            return false;
        if (minValue != other.minValue)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]", minValue, maxValue);
    }

    public int[] toArray() {
        return new int[] { getMinValue(), getMaxValue() };
    }
}
