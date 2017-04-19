/*
 * @author caojiayao 2017年4月18日 下午4:57:42
 */
package com.iwjw.fin.handler.service.annotation;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p><p>
 * @author caojiayao 
 * @version $Id: HandlerService.java, v 0.1 2017年4月18日 下午4:57:42 caojiayao Exp $
 */
@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HandlerService {

}
