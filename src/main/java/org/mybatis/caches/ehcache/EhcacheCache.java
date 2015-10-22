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

import org.apache.ibatis.cache.Cache;
import com.kancolle.server.utils.SpringUtils;


public class EhcacheCache extends AbstractEhcacheCache implements Cache {

    private static final String DEFAULT_CACHE_FACTORY_BEAN_NAME = "mybatisCacheDefaultFactory";

    private String cacheFactoryBeanName;

    public EhcacheCache(String id) {
        super(id);

        String beanName = cacheFactoryBeanName != null ? cacheFactoryBeanName : DEFAULT_CACHE_FACTORY_BEAN_NAME;
        EhcacheFactory cacheFactory = SpringUtils.getBean(beanName);
        if (cacheFactory == null) {
            throw new RuntimeException("can not found CacheFactory, need a bean name is:" + beanName);
        }

        this.cache = cacheFactory.getCache(id);
    }
}