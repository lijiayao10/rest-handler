/*
 * @author caojiayao 2017年4月19日 上午11:49:19
 */
package com.iwjw.fin.handler.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.iwjw.fin.handler.dispatch.DispatcherHandler;

@Retention(RUNTIME)
@Target(TYPE)
/**
 * <p>
 * <p>
 * 
 * @author caojiayao
 * @version $Id: EnableHandler.java, v 0.1 2017年4月19日 上午11:49:19 caojiayao Exp $
 */
@Import(DispatcherHandler.class)
public @interface EnableHandler {

}
