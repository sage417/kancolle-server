package com.kancolle.server.web.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

@Component(value = "urlCachingFilter")
public class UrlCacheFilter extends SimplePageCachingFilter {
    @Autowired
    EhCacheCacheManager cacheManager;

    @Override
    protected CacheManager getCacheManager() {
        return this.cacheManager.getCacheManager();
    }

    @Override
    protected String calculateKey(HttpServletRequest httpRequest) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(httpRequest.getMethod()).append(httpRequest.getRequestURI());
        String key = stringBuffer.toString();
        return key;
    }

    @Override
    protected boolean acceptsGzipEncoding(HttpServletRequest request) {
        return false;
    }
}
