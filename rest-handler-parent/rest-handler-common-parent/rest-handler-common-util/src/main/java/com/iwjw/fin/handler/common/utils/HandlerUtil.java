/*
 * @author caojiayao 2017年4月18日 下午3:33:50
 */
package com.iwjw.fin.handler.common.utils;

import com.iwjw.fin.handler.common.domain.Response;
import com.iwjw.fin.handler.common.enums.ResultCodeEnum;
import com.iwjw.fin.handler.common.exception.HandlerException;

/**
 * <p>处理器工具<p>
 * @author caojiayao 
 * @version $Id: HandlerUtil.java, v 0.1 2017年4月18日 下午3:33:50 caojiayao Exp $
 */
public class HandlerUtil {

    /**
     * 构建响应
     * @param result
     * @param resultCodeEnum
     * @return
     */
    public static Response buildResponse(Object result, ResultCodeEnum resultCodeEnum) {
        return new Response(resultCodeEnum.name(), result);
    }

    /**
     * 构建响应
     * @param result
     * @param resultCodeEnum
     * @return
     */
    public static Response buildResponse(HandlerException handlerException,
                                       ResultCodeEnum resultCodeEnum) {
        return buildResponse(handlerException.getErrorCodeEnum().name(), handlerException.getErrorMessage(), resultCodeEnum);
    }

    /**
     * 构建响应
     * @param result
     * @param resultCodeEnum
     * @return
     */
    public static Response buildResponse(String errorCode, String errorMessage,
                                       ResultCodeEnum resultCodeEnum) {
        return new Response(resultCodeEnum.name(), errorCode, errorMessage);
    }

}
