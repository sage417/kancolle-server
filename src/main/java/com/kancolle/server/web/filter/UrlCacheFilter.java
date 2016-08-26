package com.kancolle.server.web.filter;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import javax.servlet.http.HttpServletRequest;

public class UrlCacheFilter extends SimplePageCachingFilter {

    private EhCacheCacheManager cacheManager;

    @Autowired
    public void setCacheManager(EhCacheCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    protected CacheManager getCacheManager() {
        return this.cacheManager.getCacheManager();
    }

    @Override
    protected String calculateKey(HttpServletRequest httpRequest) {
        return httpRequest.getMethod() + httpRequest.getRequestURI();
    }

    @Override
    protected boolean acceptsGzipEncoding(HttpServletRequest request) {
        // let nginx do gzip
        return false;
    }
}
