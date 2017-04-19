/*
 * @author caojiayao 2017年4月18日 下午12:06:52
 */
package com.iwjw.fin.handler.service;

import java.util.Set;

import com.iwjw.fin.handler.common.domain.Response;

/**
 * <p>
 * 处理器接口
 * <p>
 * 
 * @author caojiayao
 * @version $Id: IHandler.java, v 0.1 2017年4月18日 下午12:06:52 caojiayao Exp $
 */
public interface IHandler {

	/**
	 * 处理方法
	 * 
	 * @param methodName
	 * @param params
	 * @return
	 */
	public Response handler(String methodName, String[] params);

	/**
	 * 输出信息
	 * 
	 * @param methodName
	 * @param params
	 * @return
	 */
	public Set<String> info();

}
