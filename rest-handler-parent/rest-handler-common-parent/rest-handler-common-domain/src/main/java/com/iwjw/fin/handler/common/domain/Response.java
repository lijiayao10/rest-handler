/*
 * @author caojiayao 2017年4月18日 下午3:52:51
 */
package com.iwjw.fin.handler.common.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>响应对象<p>
 * @author caojiayao 
 * @version $Id: Response.java, v 0.1 2017年4月18日 下午3:52:51 caojiayao Exp $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {
    private static final long serialVersionUID = -2253380911043438025L;

    /** 是否请求成功 **/
    private boolean           success          = true;
    /** 结果code **/
    private String            resultCode;

    /** 错误code **/
    private String            errorCode;
    /** 错误信息 **/
    private String            errorMessage;
    /** 结果对象 **/
    private Object            result;

    /**
     * 构建成功请求
     * @param resultCode
     * @param result
     */
    public Response(String resultCode, Object result) {
        this.resultCode = resultCode;
        this.result = result;
    }

    /**
     * 构建失败请求
     * @param errorCode
     * @param errorMessage
     */
    public Response(String resultCode, String errorCode, String errorMessage) {
        this.resultCode = resultCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
