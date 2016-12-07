package com.kancolle.server.utils;

import com.kancolle.server.dao.base.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ResolvableType;
import org.springframework.util.ClassUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaoUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DaoUtils.class);

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

    public static Class<?> getSuperClassGenricType(Class<?> clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {
        Class<?> userClass = ClassUtils.getUserClass(clazz);
        return ResolvableType.forClass(userClass).as(userClass.getSuperclass()).getGeneric(index).resolve();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T setBean(BaseDao<T> dao, Class<?>[] parameterTypes, Object[] parameters, String... exclude) throws InstantiationException, IllegalAccessException {

        Class<T> targetClass = (Class<T>) getSuperClassGenricType(dao.getClass());

        T instance = targetClass.newInstance();

        List<String> excludeList = Arrays.asList(exclude);

        Arrays.stream(targetClass.getMethods()).filter(IS_SET_METHOD).filter(method -> !excludeList.contains(method.getName())).forEach(method -> {
            try {
                Method daoMethod = dao.getClass().getMethod(GET_PARAM_NAME.apply(method.getName()), parameterTypes);
                method.invoke(instance, daoMethod.invoke(dao, parameters));
            } catch (Exception e) {
                LOGGER.error("Error Happen in setBean", e);
            }
        });
        return instance;
    }
}
