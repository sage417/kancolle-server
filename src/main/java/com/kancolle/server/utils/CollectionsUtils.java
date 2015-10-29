package com.kancolle.server.utils;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

import com.google.common.collect.Lists;

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

    public static <A extends T, B extends T, T> List<T> listAdd(List<A> list1, List<B> list2) {
        int newSize = list1.size() + list2.size();
        List<T> newList = Lists.newArrayListWithCapacity(newSize);
        newList.addAll(list1);
        newList.addAll(list2);
        return newList;
    }
}
