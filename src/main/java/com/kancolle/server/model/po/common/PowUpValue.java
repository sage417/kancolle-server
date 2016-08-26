/**
 *
 */
package com.kancolle.server.model.po.common;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author J.K.SAGE
 * @Date 2015年5月30日
 */
public class PowUpValue implements Serializable {

    private static final long serialVersionUID = 3413235819803368244L;

    /** 火力 */
    private final int houg;

    /** 雷装 */
    private final int raig;

    /** 对空 */
    private final int tyku;

    /** 装甲 */
    private final int souk;

    private final int[] powUpArr;

    public PowUpValue(final int houg, final int raig, final int tyku, final int souk) {
        this.houg = houg;
        this.raig = raig;
        this.tyku = tyku;
        this.souk = souk;
        this.powUpArr = new int[]{houg, raig, tyku, souk};
    }

    public int getHoug() {
        return houg;
    }


    public int getRaig() {
        return raig;
    }


    public int getTyku() {
        return tyku;
    }


    public int getSouk() {
        return souk;
    }

    public int[] getPowUpArr() {
        return Arrays.copyOf(this.powUpArr, this.powUpArr.length);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + houg;
        result = prime * result + raig;
        result = prime * result + souk;
        result = prime * result + tyku;
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
        PowUpValue other = (PowUpValue) obj;
        if (houg != other.houg)
            return false;
        if (raig != other.raig)
            return false;
        if (souk != other.souk)
            return false;
        if (tyku != other.tyku)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s,%s,%s]", houg, raig, tyku, souk);
    }
}
