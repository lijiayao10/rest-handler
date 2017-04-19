/*
 * @author caojiayao 2017年4月18日 下午3:00:26
 */
package com.iwjw.fin.handler.common.exception;


import com.iwjw.fin.handler.common.enums.ErrorCodeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>处理器异常<p>
 * @author caojiayao 
 * @version $Id: HandlerException.java, v 0.1 2017年4月18日 下午3:00:26 caojiayao Exp $
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class HandlerException extends Exception {

    /**  **/
    private static final long serialVersionUID = 1L;

    /** 异常code **/
    private ErrorCodeEnum     errorCodeEnum;
    /** 异常信息 **/
    private String            errorMessage;

    /**
     * 
     */
    public HandlerException(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
        this.errorMessage = errorCodeEnum.getErrorMessage();
    }

}
