/**
 *    Copyright 2010-2015 the original author or authors.
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

import java.util.concurrent.locks.ReadWriteLock;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.ibatis.cache.Cache;

/**
 * Cache adapter for Ehcache.
 *
 * @author Simone Tripodi
 */
public abstract class AbstractEhcacheCache implements Cache {

    /**
     * The cache id (namespace)
     */
    protected final String id;

    /**
     * The cache instance
     */
    protected Ehcache cache;
    
    /**
     * @param id
     */
    public AbstractEhcacheCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        cache.removeAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getObject(Object key) {
        Element cachedElement = cache.get(key);
        if (cachedElement == null) {
            return null;
        }
        return cachedElement.getObjectValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return cache.getSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putObject(Object key, Object value) {
        cache.put(new Element(key, value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object removeObject(Object key) {
        Object obj = getObject(key);
        cache.remove(key);
        return obj;
    }

    /**
     * {@inheritDoc}
     */
    public void unlock(Object key) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Cache)) {
            return false;
        }

        Cache otherCache = (Cache) obj;
        return id.equals(otherCache.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
      return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "EHCache {"
                + id
                + "}";
    }

    // DYNAMIC PROPERTIES
    public void setEternal(boolean eternal) {
        cache.getCacheConfiguration().setEternal(eternal);
    }

    /**
     * Sets the time to idle for an element before it expires. Is only used if the element is not eternal.
     *
     * @param timeToIdleSeconds the default amount of time to live for an element from its last accessed or modified date
     */
    public void setTimeToIdleSeconds(long timeToIdleSeconds) {
        cache.getCacheConfiguration().setTimeToIdleSeconds(timeToIdleSeconds);
    }

    /**
     * Sets the time to idle for an element before it expires. Is only used if the element is not eternal.
     *
     * @param timeToLiveSeconds the default amount of time to live for an element from its creation date
     */
    public void setTimeToLiveSeconds(long timeToLiveSeconds) {
        cache.getCacheConfiguration().setTimeToLiveSeconds(timeToLiveSeconds);
    }

    /**
     * Sets the maximum objects to be held in memory (0 = no limit).
     *
     * @param maxElementsInMemory The maximum number of elements in memory, before they are evicted (0 == no limit)
     */
    public void setMaxEntriesLocalHeap(long maxEntriesLocalHeap) {
        cache.getCacheConfiguration().setMaxEntriesLocalHeap(maxEntriesLocalHeap);
    }

    /**
     * Sets the maximum number elements on Disk. 0 means unlimited.
     *
     * @param maxElementsOnDisk the maximum number of Elements to allow on the disk. 0 means unlimited.
     */
    public void setMaxEntriesLocalDisk(long maxEntriesLocalDisk) {
        cache.getCacheConfiguration().setMaxEntriesLocalDisk(maxEntriesLocalDisk);
    }

    /**
     * Sets the eviction policy. An invalid argument will set it to null.
     *
     * @param memoryStoreEvictionPolicy a String representation of the policy. One of "LRU", "LFU" or "FIFO".
     */
    public void setMemoryStoreEvictionPolicy(String memoryStoreEvictionPolicy) {
        cache.getCacheConfiguration().setMemoryStoreEvictionPolicy(memoryStoreEvictionPolicy);
    }

}
