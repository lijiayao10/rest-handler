/*
 * @author caojiayao 2017年4月18日 下午4:31:38
 */
package com.iwjw.fin.handler.service.impl;

import static com.iwjw.fin.handler.common.enums.ErrorCodeEnum.OTHER_ERROR;
import static com.iwjw.fin.handler.common.enums.ErrorCodeEnum.PARAMETER_INVALID;
import static com.iwjw.fin.handler.common.enums.ErrorCodeEnum.SERVICE_NOT_FOUND;
import static com.iwjw.fin.handler.common.enums.ResultCodeEnum.F;
import static com.iwjw.fin.handler.common.enums.ResultCodeEnum.S;
import static com.iwjw.fin.handler.common.utils.HandlerUtil.buildResponse;
import static com.iwjw.fin.handler.common.utils.JsonUtil.parse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Maps;
import com.iwjw.fin.handler.common.domain.Response;
import com.iwjw.fin.handler.common.exception.HandlerException;
import com.iwjw.fin.handler.service.IHandler;

/**
 * <p>
 * 工厂处理器
 * <p>
 * 
 * @author caojiayao
 * @version $Id: FactoryHandler.java, v 0.1 2017年4月18日 下午4:31:38 caojiayao Exp $
 */
public class FactoryHandler implements IHandler {

	/** 具体服务 **/
	private Object target;

	/** 方法名方法映射 **/
	private Map<String, Method> methodMap = Maps.newHashMap();

	/**
	 * @throws Exception
	 * 
	 */
	public FactoryHandler(Object target) {
		this.target = target;
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化
	 * 
	 * @throws Exception
	 */
	protected void init() throws Exception {
		// 将代理服务方法缓存
		for (Method method : target.getClass().getDeclaredMethods()) {
			if (Modifier.isPublic(method.getModifiers()))
				methodMap.put(method.getName(), method);
		}
	}

	@Override
	public Response handler(String methodName, String[] params) {

		try {
			verify(methodName, params);

			Method method = getMethod(methodName);

			return buildResponse(execute(method, convert(method.getParameterTypes(), params)), S);

		} catch (HandlerException e) {
			return buildResponse(e, F);
		} catch (IllegalArgumentException e) {
			return buildResponse(PARAMETER_INVALID.name(), e.getMessage(), F);
		} catch (Exception e) {
			return buildResponse(OTHER_ERROR.name(), e.getMessage(), F);
		}
	}

	/**
	 * 执行请求
	 * 
	 * @param method
	 * @param objects
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	protected Object execute(Method method, Object... objects)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return method.invoke(target, objects);
	}

	/**
	 * 校验参数
	 * 
	 * @param methodName
	 * @param params
	 * @throws HandlerException
	 */
	protected void verify(String methodName, String[] params) throws HandlerException {
		if (StringUtils.isEmpty(methodName))
			throw new HandlerException(PARAMETER_INVALID);
	}

	/**
	 * 转换类型
	 * 
	 * @param paramClass
	 * @param params
	 * @return
	 * @throws HandlerException
	 */
	protected Object[] convert(Class<?>[] paramClass, String[] params) throws HandlerException {
		if (paramClass != null && params != null)
			if (paramClass.length != params.length)
				throw new HandlerException(PARAMETER_INVALID);
			else {

				Object[] objects = new Object[paramClass.length];
				for (int i = 0; i < paramClass.length; i++) {
					objects[i] = parse(params[i], paramClass[i]);
				}

				return objects;
			}

		return null;
	}

	/**
	 * 获取执行方法
	 * 
	 * @param methodName
	 * @return
	 * @throws HandlerException
	 */
	protected Method getMethod(String methodName) throws HandlerException {
		Method method = methodMap.get(methodName);
		if (method == null)
			throw new HandlerException(SERVICE_NOT_FOUND);
		return method;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netfinworks.deposit.web.IHandler#info()
	 */
	@Override
	public Set<String> info() {
		return methodMap.keySet();
	}

}
