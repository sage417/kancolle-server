package com.kancolle.server.model.po.duty;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("Duty")
public class Duty implements Serializable {

    private static final long serialVersionUID = 5910310852645358322L;

    private int no;

    private int category;

    private int type;

    private String title;

    private String detail;

    private int[] material;

    private int bonusFlag;

    private int invalidFlag;

    private int[] winItem1;

    private int[] winItem2;

    private Duty parent;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int[] getMaterial() {
        return material;
    }

    public void setMaterial(int[] material) {
        this.material = material;
    }

    public int getBonusFlag() {
        return bonusFlag;
    }

    public void setBonusFlag(int bonusFlag) {
        this.bonusFlag = bonusFlag;
    }

    public int getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(int invalidFlag) {
        this.invalidFlag = invalidFlag;
    }

    public int[] getWinItem1() {
        return winItem1;
    }

    public void setWinItem1(int[] winItem1) {
        this.winItem1 = winItem1;
    }

    public int[] getWinItem2() {
        return winItem2;
    }

    public void setWinItem2(int[] winItem2) {
        this.winItem2 = winItem2;
    }

    public Duty getParent() {
        return parent;
    }

    public void setParent(Duty parent) {
        this.parent = parent;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + no;
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
        Duty other = (Duty) obj;
        if (no != other.no)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Duty [title=" + title + "]";
    }
}
