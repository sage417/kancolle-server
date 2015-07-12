/**
 * 
 */
package com.kancolle.server.model.po.resource;

import org.apache.ibatis.type.Alias;

/**
 * @author J.K.SAGE
 * @Date 2015年6月17日
 *
 */
@Alias("Resource")
public class Resource {
    public static final int MAX_RESOURCE_VALUE = 300000;

    public static final int MAX_METERIAL_VALUE = 3000;

    private long memberId;

    private int fuel;

    private int bull;

    private int steel;

    private int bauxite;

    private int fastRecovery;

    private int fastBuild;

    private int devItem;

    private int ehItem;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
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

    public int getSteel() {
        return steel;
    }

    public void setSteel(int steel) {
        this.steel = steel;
    }

    public int getBauxite() {
        return bauxite;
    }

    public void setBauxite(int bauxite) {
        this.bauxite = bauxite;
    }

    public int getFastRecovery() {
        return fastRecovery;
    }

    public void setFastRecovery(int fastRecovery) {
        this.fastRecovery = fastRecovery;
    }

    public int getFastBuild() {
        return fastBuild;
    }

    public void setFastBuild(int fastBuild) {
        this.fastBuild = fastBuild;
    }

    public int getDevItem() {
        return devItem;
    }

    public void setDevItem(int devItem) {
        this.devItem = devItem;
    }

    public int getEhItem() {
        return ehItem;
    }

    public void setEhItem(int ehItem) {
        this.ehItem = ehItem;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (memberId ^ (memberId >>> 32));
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
        Resource other = (Resource) obj;
        if (memberId != other.memberId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Resource [memberId=%s, fuel=%s, bull=%s, steel=%s, bauxite=%s, fastRecovery=%s, fastBuild=%s, devItem=%s, ehItem=%s]", memberId, fuel, bull, steel, bauxite,
                fastRecovery, fastBuild, devItem, ehItem);
    }

    private boolean isEnough(int value, int target) {
        return target > 0 && value >= target;
    }

    public boolean hasEnoughFuel(int target) {
        return isEnough(this.fuel, target);
    }

    public boolean hasEnoughBull(int target) {
        return isEnough(this.bull, target);
    }

    public boolean hasEnoughSteel(int target) {
        return isEnough(this.steel, target);
    }

    public boolean hasEnoughBauxite(int target) {
        return isEnough(this.bauxite, target);
    }

    public boolean hasEnoughFastRecovery(int target) {
        return isEnough(this.fastRecovery, target);
    }

    public boolean hasEnoughFastBuild(int target) {
        return isEnough(this.fastBuild, target);
    }

    public boolean hasEnoughDevItem(int target) {
        return isEnough(this.devItem, target);
    }

    public boolean hasEnoughEhItem(int target) {
        return isEnough(this.ehItem, target);
    }
}
