package com.kancolle.server.utils;

import org.apache.commons.lang3.RandomUtils;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class CollectionsUtils {

    public static <T> T randomGet(List<T> list) {
        checkArgument(!list.isEmpty());
        return notEmptyRandomGet(list);
    }

    private static <T> T notEmptyRandomGet(List<T> list) {
        int size = list.size();

        if (size == 1) {
            return list.get(0);
        }
        return list.get(RandomUtils.nextInt(0, size));
    }

}
