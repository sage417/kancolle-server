package com.kancolle.server.controller.monitor.model;

import org.springframework.beans.BeanUtils;

import net.sf.ehcache.Statistics;

public class StatisticsModel {

    private String cacheName;

    private int statisticsAccuracy;

    private long cacheHits;

    private long onDiskHits;

    private long offHeapHits;

    private long inMemoryHits;

    private long misses;

    private long onDiskMisses;

    private long offHeapMisses;

    private long inMemoryMisses;

    private long size;

    private long memoryStoreSize;

    private long offHeapStoreSize;

    private long diskStoreSize;

    private float averageGetTime;

    private long evictionCount;

    private long searchesPerSecond;

    private long averageSearchTime;

    private long writerQueueLength;

    public StatisticsModel() {
    }

    public StatisticsModel(Statistics statistics) {
        BeanUtils.copyProperties(statistics, this);
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public int getStatisticsAccuracy() {
        return statisticsAccuracy;
    }

    public void setStatisticsAccuracy(int statisticsAccuracy) {
        this.statisticsAccuracy = statisticsAccuracy;
    }

    public long getCacheHits() {
        return cacheHits;
    }

    public void setCacheHits(long cacheHits) {
        this.cacheHits = cacheHits;
    }

    public long getOnDiskHits() {
        return onDiskHits;
    }

    public void setOnDiskHits(long onDiskHits) {
        this.onDiskHits = onDiskHits;
    }

    public long getOffHeapHits() {
        return offHeapHits;
    }

    public void setOffHeapHits(long offHeapHits) {
        this.offHeapHits = offHeapHits;
    }

    public long getInMemoryHits() {
        return inMemoryHits;
    }

    public void setInMemoryHits(long inMemoryHits) {
        this.inMemoryHits = inMemoryHits;
    }

    public long getMisses() {
        return misses;
    }

    public void setMisses(long misses) {
        this.misses = misses;
    }

    public long getOnDiskMisses() {
        return onDiskMisses;
    }

    public void setOnDiskMisses(long onDiskMisses) {
        this.onDiskMisses = onDiskMisses;
    }

    public long getOffHeapMisses() {
        return offHeapMisses;
    }

    public void setOffHeapMisses(long offHeapMisses) {
        this.offHeapMisses = offHeapMisses;
    }

    public long getInMemoryMisses() {
        return inMemoryMisses;
    }

    public void setInMemoryMisses(long inMemoryMisses) {
        this.inMemoryMisses = inMemoryMisses;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getMemoryStoreSize() {
        return memoryStoreSize;
    }

    public void setMemoryStoreSize(long memoryStoreSize) {
        this.memoryStoreSize = memoryStoreSize;
    }

    public long getOffHeapStoreSize() {
        return offHeapStoreSize;
    }

    public void setOffHeapStoreSize(long offHeapStoreSize) {
        this.offHeapStoreSize = offHeapStoreSize;
    }

    public long getDiskStoreSize() {
        return diskStoreSize;
    }

    public void setDiskStoreSize(long diskStoreSize) {
        this.diskStoreSize = diskStoreSize;
    }

    public float getAverageGetTime() {
        return averageGetTime;
    }

    public void setAverageGetTime(float averageGetTime) {
        this.averageGetTime = averageGetTime;
    }

    public long getEvictionCount() {
        return evictionCount;
    }

    public void setEvictionCount(long evictionCount) {
        this.evictionCount = evictionCount;
    }

    public long getSearchesPerSecond() {
        return searchesPerSecond;
    }

    public void setSearchesPerSecond(long searchesPerSecond) {
        this.searchesPerSecond = searchesPerSecond;
    }

    public long getAverageSearchTime() {
        return averageSearchTime;
    }

    public void setAverageSearchTime(long averageSearchTime) {
        this.averageSearchTime = averageSearchTime;
    }

    public long getWriterQueueLength() {
        return writerQueueLength;
    }

    public void setWriterQueueLength(long writerQueueLength) {
        this.writerQueueLength = writerQueueLength;
    }
}
