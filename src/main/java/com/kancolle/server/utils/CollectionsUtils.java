package com.kancolle.server.utils;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public abstract class CollectionsUtils {

    public static <T> T randomGet(List<T> list) {
        checkArgument(!list.isEmpty());
        return notEmptyRandomGet(list);
    }

    public static <T> T randomGet(Set<T> set) {
        checkArgument(!set.isEmpty());
        return randomGet(Lists.newArrayList(set));
    }

    private static <T> T notEmptyRandomGet(List<T> list) {
        int size = list.size();

        if (size == 1) {
            return list.get(0);
        }
        return list.get(RandomUtils.nextInt(0, size));
    }

    public static <K, V> Map<K, V> putAll(Map<K, V> map1, Map<K, V> map2) {
        int expectedSize = map1.size() + map2.size();
        Map<K, V> map = Maps.newHashMapWithExpectedSize(expectedSize);
        map.putAll(map1);
        map.putAll(map2);
        return map;
    }
}
