package com.kancolle.server.utils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kancolle.server.dao.base.BaseDao;

public class DaoUtils {
	private static final String STR_SET = "set";
	private static final String STR_GET = "get";
	private static final String PARAM_PREFIX = "api";

	private static final Pattern METHOD_NAME_PATTERN_REPLACER = Pattern
			.compile("_[a-zA-Z0-9]");

	private static final Predicate<Method> IS_SET_METHOD = method -> method
			.getName().startsWith(STR_SET);

	private static final Function<String, String> GET_PARAM_NAME = method_name -> {
		String raw_name = method_name.substring(STR_SET.length()
				+ PARAM_PREFIX.length());
		Matcher matcher = METHOD_NAME_PATTERN_REPLACER.matcher(raw_name);

		while (matcher.find()) {
			raw_name = matcher.replaceFirst(matcher.group().substring(1)
					.toUpperCase());
			matcher = METHOD_NAME_PATTERN_REPLACER.matcher(raw_name);
		}
		return STR_GET + raw_name;
	};

	@SuppressWarnings("unchecked")
	public static <T> T setObject(BaseDao<T> dao, Class<?>[] parameterTypes,
			Object[] parameters) throws InstantiationException,
			IllegalAccessException {
		Type genType = dao.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		Class<T> clazz = (Class<T>) params[0];
		T instance = clazz.newInstance();

		Arrays.asList(clazz.getMethods())
				.stream()
				.filter(IS_SET_METHOD)
				.forEach(
						method -> {
							try {
								Method daoMethod = dao.getClass().getMethod(
										GET_PARAM_NAME.apply(method.getName()),
										parameterTypes);
								method.invoke(instance,
										daoMethod.invoke(dao, parameters));
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
		return instance;
	}
}
