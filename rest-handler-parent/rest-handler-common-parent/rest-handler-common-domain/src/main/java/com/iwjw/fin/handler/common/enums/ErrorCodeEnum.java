/*
 * @author caojiayao 2017年4月18日 下午3:02:09
 */
package com.iwjw.fin.handler.common.enums;
/**
 * <p><p>
 * @author caojiayao 
 * @version $Id: ErrorCodeEnum.java, v 0.1 2017年4月18日 下午3:02:09 caojiayao Exp $
 */
public enum ErrorCodeEnum {
    
    
    PARAMETER_INVALID("请求参数不合法"),SERVICE_NOT_FOUND("请求服务不存在"),SERVICE_ERROR("请求服务异常"),OTHER_ERROR("其他异常");
    
    
    final String errorMessage;
    
    /**
     * 
     */
    ErrorCodeEnum(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
