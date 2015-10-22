/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.kancolle.server.controller.monitor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.kancolle.server.utils.PrettyMemoryUtils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 13-6-8 下午5:17
 * <p>
 * Version: 1.0
 */
@RestController
@RequestMapping("/admin/monitor/ehcache")
public class EhcacheMonitorController {

    @Autowired
    private CacheManager cacheManager;

    @RequestMapping()
    public Map<String, Cache> index() {
        return Arrays.stream(cacheManager.getCacheNames()).collect(Collectors.toMap(name -> name, cacheManager::getCache));
    }

    @RequestMapping("{cacheName}/details")
    public List<Object> details(@PathVariable("cacheName") String cacheName, @RequestParam(value = "searchText", required = false, defaultValue = "") String searchText) {
        List<?> allKeys = cacheManager.getCache(cacheName).getKeys();
        List<Object> showKeys = allKeys.stream().filter(key -> key.toString().contains(searchText)).collect(Collectors.toList());

        return showKeys;
    }

    @RequestMapping("{cacheName}/{key}/details")
    public Object keyDetail(@PathVariable("cacheName") String cacheName, @PathVariable("key") String key) {

        Element element = cacheManager.getCache(cacheName).get(key);

        String dataPattern = "yyyy-MM-dd hh:mm:ss";
        Map<String, Object> data = Maps.newHashMap();
        data.put("objectValue", element.getObjectValue().toString());
        data.put("size", PrettyMemoryUtils.prettyByteSize(element.getSerializedSize()));
        data.put("hitCount", element.getHitCount());

        Date latestOfCreationAndUpdateTime = new Date(element.getLatestOfCreationAndUpdateTime());
        data.put("latestOfCreationAndUpdateTime", DateFormatUtils.format(latestOfCreationAndUpdateTime, dataPattern));
        Date lastAccessTime = new Date(element.getLastAccessTime());
        data.put("lastAccessTime", DateFormatUtils.format(lastAccessTime, dataPattern));
        if (element.getExpirationTime() == Long.MAX_VALUE) {
            data.put("expirationTime", "不过期");
        } else {
            Date expirationTime = new Date(element.getExpirationTime());
            data.put("expirationTime", DateFormatUtils.format(expirationTime, dataPattern));
        }

        data.put("timeToIdle", element.getTimeToIdle());
        data.put("timeToLive", element.getTimeToLive());
        data.put("version", element.getVersion());

        return data;
    }

    @RequestMapping("{cacheName}/{key}/delete")
    public Object delete(@PathVariable("cacheName") String cacheName, @PathVariable("key") String key) {
        Cache cache = cacheManager.getCache(cacheName);
        cache.remove(key);
        return "操作成功！";
    }

    @RequestMapping("{cacheName}/clear")
    public Object clear(@PathVariable("cacheName") String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        cache.clearStatistics();
        cache.removeAll();
        return "操作成功！";
    }
}