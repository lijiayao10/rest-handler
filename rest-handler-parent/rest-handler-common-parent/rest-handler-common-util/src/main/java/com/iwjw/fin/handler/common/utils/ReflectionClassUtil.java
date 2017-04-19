/*
 * @author caojiayao 2017年4月19日 下午2:52:26
 */
package com.iwjw.fin.handler.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * <p>
 * <p>
 * 
 * @author caojiayao
 * @version $Id: ReflectionClass.java, v 0.1 2017年4月19日 下午2:52:26 caojiayao Exp
 *          $
 */
public class ReflectionClassUtil {

	/**  **/
	private static final String VALUE_KEY = "value";
	/**  **/
	private static final String NAME_KEY = "name";
	/**  **/
	private static final String METHOD_NAME_KEY = "methodName";
	/**  **/
	public static final String TYPE_KEY = "type";

	/**  **/
	public static final String RETURN_KEY = "return";

	/**  **/
	public static final String PARAMS_KEY = "params";

	/**  **/
	public static final String PARAM_KEY = "param";

	/**
	 * 获取属性值
	 * 
	 * @param field
	 * @param bean
	 * @return
	 */
	public static Object getFieldValue(Field field, Object bean) {
		try {
			field.setAccessible(false);
			return field.get(bean);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取属性类型(type)，属性名(name) list
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Map<String, Object>> getFileds(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();

		List<Map<String, Object>> list = Lists.newArrayList();

		Map<String, Object> infoMap = null;
		for (Field field : fields) {
			infoMap = Maps.newHashMap();
			infoMap.put(TYPE_KEY, field.getType().toString());
			infoMap.put(NAME_KEY, field.getName());
			list.add(infoMap);
		}
		return list;
	}

	/**
	 * 获取属性类型(type)，属性名(name) ,属性值(value)list
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Map<String, Object>> getFileds(Object bean) {
		Field[] fields = bean.getClass().getDeclaredFields();
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> infoMap = null;
		for (Field field : fields) {
			infoMap = Maps.newHashMap();
			infoMap.put(TYPE_KEY, field.getType().toString());
			infoMap.put(NAME_KEY, field.getName());
			infoMap.put(VALUE_KEY, getFieldValue(field, bean));
			list.add(infoMap);
		}
		return list;
	}

	/**
	 * 获取属性类型(type)，属性名(name) ,属性值(value)list
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Map<String, Object>> getMethods(Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		List<Map<String, Object>> list = Lists.newArrayList();
		Map<String, Object> infoMap = null;
		for (Method method : methods) {
			if (Modifier.isPublic(method.getModifiers())) {
				infoMap = Maps.newHashMap();
				infoMap.put(METHOD_NAME_KEY, method.getName());
				infoMap.put(PARAMS_KEY, getMethodParams(method.getParameterTypes()));
				infoMap.put(RETURN_KEY, method.getReturnType().getName());
				list.add(infoMap);
			}
		}
		return list;
	}

	public static Map<String, Object> getMethodParams(Class<?>[] paramTypes) {
		if (paramTypes != null) {
			Map<String, Object> map = Maps.newHashMap();
			for (int i = 0; i < paramTypes.length; i++) {
				map.put(PARAM_KEY + i, getFileds(paramTypes[i]));
			}
			return map;
		} else
			return MapUtils.EMPTY_MAP;
	}

}
