package com.kancolle.server.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class BeanUtils {

    private BeanUtils() {
    }

    public static <T> Stream<Method> getGetMethodStream(Class<T> clazz, Class<?> returnType) {
        return getMethodStream(clazz).filter(method -> method.getName().startsWith("get") && returnType.equals(method.getReturnType()));
    }

    public static <T> Stream<Method> getSetMethodStream(Class<T> clazz, Class<?> paramType) {
        return getMethodStream(clazz).filter(method -> method.getName().startsWith("set") && method.getParameterCount() == 1 && paramType.equals(method.getParameterTypes()[0]));
    }

    public static <T> Stream<Method> getMethodStream(Class<T> clazz) {
        return Arrays.asList(clazz.getMethods()).stream();
    }
}
