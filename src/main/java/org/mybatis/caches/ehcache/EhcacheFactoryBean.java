package org.mybatis.caches.ehcache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

public class EhcacheFactoryBean implements EhcacheFactory {

  private CacheManager cacheManager;

  @Override
  public Ehcache getCache(String id) {

    if (!cacheManager.cacheExists(id)) {
      cacheManager.addCache(id);
    }

    return cacheManager.getEhcache(id);
  }

  public CacheManager getCacheManager() {
    return cacheManager;
  }

  public void setCacheManager(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }
}
