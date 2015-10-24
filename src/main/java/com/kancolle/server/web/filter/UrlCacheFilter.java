package com.kancolle.server.web.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

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
        StringBuilder builder = new StringBuilder(httpRequest.getMethod());
        builder.append(httpRequest.getRequestURI());
        return builder.toString();
    }

    @Override
    protected boolean acceptsGzipEncoding(HttpServletRequest request) {
        // Let nginx do gzip
        return false;
    }
}
