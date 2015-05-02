package com.kancolle.server.utils;

import com.alibaba.fastjson.JSONArray;

public class JSONUtils {

    public static int[] toIntArray(JSONArray array, int size) {
        int arraySize = array.size() + size;
        int[] ints = new int[arraySize];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = array.getIntValue(i);
        }
        return ints;
    }

    public static long[] toLongArray(JSONArray array, int size) {
        int arraySize = array.size() + size;
        long[] longs = new long[arraySize];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = array.getLongValue(i);
        }
        return longs;
    }

    public static String[] toStringArray(JSONArray array, int size) {
        int arraySize = array.size() + size;
        return array.toArray(new String[arraySize]);
    }
}
