package com.kancolle.server.utils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.kancolle.server.dao.annotation.Column;
import com.kancolle.server.dao.annotation.Id;
import com.kancolle.server.dao.base.BaseDao;

public class DaoUtils {
    private static final String PARAM_PREFIX = "api";

    private static final Pattern METHOD_NAME_PATTERN_REPLACER = Pattern.compile("_[a-zA-Z0-9]");

    private static final Predicate<Method> IS_SET_METHOD = method -> method.getName().startsWith(BeanUtils.STR_SET) && method.getParameterCount() == 1;

    private static final Function<String, String> GET_PARAM_NAME = method_name -> {
        String raw_name = method_name.substring(BeanUtils.STR_SET.length() + PARAM_PREFIX.length());
        Matcher matcher = METHOD_NAME_PATTERN_REPLACER.matcher(raw_name);

        while (matcher.find()) {
            raw_name = matcher.replaceFirst(matcher.group().substring(1).toUpperCase());
            matcher = METHOD_NAME_PATTERN_REPLACER.matcher(raw_name);
        }
        return BeanUtils.STR_GET + raw_name;
    };

    @SuppressWarnings("unchecked")
    public static <T> T setBean(BaseDao<T> dao, Class<?>[] parameterTypes, Object[] parameters, String... exclude) throws InstantiationException, IllegalAccessException {

        Class<T> targetClass = (Class<T>) getSuperClassGenricType(dao.getClass());

        T instance = targetClass.newInstance();

        List<String> excludeList = Arrays.asList(exclude);

        Arrays.asList(targetClass.getMethods()).parallelStream().filter(IS_SET_METHOD).filter(method -> !excludeList.contains(method.getName())).forEach(method -> {
            try {
                Method daoMethod = dao.getClass().getMethod(GET_PARAM_NAME.apply(method.getName()), parameterTypes);
                method.invoke(instance, daoMethod.invoke(dao, parameters));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return instance;
    }

    public static Class<?> getSuperClassGenricType(Class<?> clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass();

        while (!(genType instanceof ParameterizedType)) {
            clazz = clazz.getSuperclass();
            genType = clazz.getGenericSuperclass();
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[index];
    }

    public static void setObject(Object target, ResultSet rs) {
        Arrays.asList(target.getClass().getDeclaredMethods()).stream().filter(IS_SET_METHOD).forEach(method -> {
            String name;
            Class<?> type;

            Id[] ids = method.getAnnotationsByType(Id.class);
            if (ids.length != 0) {

                Id id = ids[0];
                name = id.name();
                type = id.type();
            } else {
                Column[] columms = method.getAnnotationsByType(Column.class);
                if (columms.length == 0) {
                    System.out.println(method);
                    return;
                }

                Column columm = columms[0];
                name = columm.name();
                type = columm.type();
            }

            try {
                if (type == int.class) {
                    method.invoke(target, rs.getInt(name));
                    return;
                }
                if (type == String.class) {
                    method.invoke(target, rs.getString(name));
                    return;
                }
                if (type == long.class) {
                    method.invoke(target, rs.getLong(name));
                    return;
                }
                if (type == double.class) {
                    method.invoke(target, rs.getDouble(name));
                }
                if (type == JSONObject.class) {
                    method.invoke(target, rs.getString(name));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
