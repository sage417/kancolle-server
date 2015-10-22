package org.mybatis.caches.ehcache;

import net.sf.ehcache.Ehcache;

public interface EhcacheFactory {

    public Ehcache getCache(String id);
}
