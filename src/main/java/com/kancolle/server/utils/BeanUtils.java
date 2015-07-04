package com.kancolle.server.utils;

import java.lang.reflect.Method;
import java.util.stream.Stream;

public class BeanUtils {
    public static final String STR_SET = "set";
    public static final String STR_GET = "get";

    public static <T> Stream<Method> getGetMethodStream(Class<T> clazz, Class<?> returnType) {
        return getMethodStream(clazz).filter(method -> method.getName().startsWith(STR_GET) && method.getParameterCount() == 0 && returnType.equals(method.getReturnType()));
    }

    public static <T> Stream<Method> getMethodStream(Class<T> clazz) {
        return Stream.of(clazz.getMethods());
    }

    public static <T> Stream<Method> getSetMethodStream(Class<T> clazz) {
        return getMethodStream(clazz).filter(method -> method.getName().startsWith(STR_SET) && method.getParameterCount() == 1);
    }

    public static <T> Stream<Method> getSetMethodStream(Class<T> clazz, Class<?> paramType) {
        return getMethodStream(clazz).filter(method -> method.getName().startsWith(STR_SET) && method.getParameterCount() == 1 && paramType.equals(method.getParameterTypes()[0]));
    }

    private BeanUtils() {
    }
}
