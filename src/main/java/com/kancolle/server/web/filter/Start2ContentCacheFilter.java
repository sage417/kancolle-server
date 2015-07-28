package com.kancolle.server.web.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

@Component(value = "Start2CachingFilter")
public class Start2ContentCacheFilter extends SimplePageCachingFilter {
    @Autowired
    EhCacheCacheManager cacheManager;

    @Override
    protected CacheManager getCacheManager() {
        return this.cacheManager.getCacheManager();
    }
}
