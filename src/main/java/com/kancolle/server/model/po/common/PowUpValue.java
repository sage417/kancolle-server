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
public class PowUpValue implements Serializable {

    private static final long serialVersionUID = 3413235819803368244L;

    /** 火力 */
    private int houg;

    /** 雷装 */
    private int raig;

    /** 对空 */
    private int tyku;

    /** 装甲 */
    private int souk;

    public PowUpValue() {
        super();
    }

    public PowUpValue(int houg, int raig, int tyku, int souk) {
        super();
        this.houg = houg;
        this.raig = raig;
        this.tyku = tyku;
        this.souk = souk;
    }

    public int getHoug() {
        return houg;
    }

    public void setHoug(int houg) {
        this.houg = houg;
    }

    public int getRaig() {
        return raig;
    }

    public void setRaig(int raig) {
        this.raig = raig;
    }

    public int getTyku() {
        return tyku;
    }

    public void setTyku(int tyku) {
        this.tyku = tyku;
    }

    public int getSouk() {
        return souk;
    }

    public void setSouk(int souk) {
        this.souk = souk;
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
