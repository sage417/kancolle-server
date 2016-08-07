/*
 *    Copyright 2010-2014 The original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.caches.ehcache;

import com.kancolle.server.utils.SpringUtils;
import net.sf.ehcache.CacheManager;
import org.apache.ibatis.cache.Cache;

public class EhcacheCache extends AbstractEhcacheCache implements Cache {

    public EhcacheCache(String id) {
        super(id);

        CacheManager cacheManager = SpringUtils.getBean("ehcacheManager", CacheManager.class);

        if (!cacheManager.cacheExists(id)) {
            cacheManager.addCache(id);
        }

        this.cache = cacheManager.getCache(id);
    }
}