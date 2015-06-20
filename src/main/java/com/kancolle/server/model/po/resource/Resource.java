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

    private int steal;

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

    public int getSteal() {
        return steal;
    }

    public void setSteal(int steal) {
        this.steal = steal;
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
        return String.format("Resource [memberId=%s, fuel=%s, bull=%s, steal=%s, bauxite=%s, fastRecovery=%s, fastBuild=%s, devItem=%s, ehItem=%s]", memberId, fuel, bull, steal, bauxite,
                fastRecovery, fastBuild, devItem, ehItem);
    }

    public boolean hasEnoughFuel(int target) {
        return this.fuel >= target;
    }

    public boolean hasEnoughBull(int target) {
        return this.bull >= target;
    }

    public boolean hasEnoughSteal(int target) {
        return this.steal >= target;
    }

    public boolean hasEnoughBauxite(int target) {
        return this.bauxite >= target;
    }

    public boolean hasEnoughFastRecovery(int target) {
        return this.fastRecovery >= target;
    }

    public boolean hasEnoughFastBuild(int target) {
        return this.fastBuild >= target;
    }

    public boolean hasEnoughDevItem(int target) {
        return this.devItem >= target;
    }

    public boolean hasEnoughEhItem(int target) {
        return this.ehItem >= target;
    }

}
