/*
 * @author caojiayao 2017年4月19日 下午5:11:02
 */
package com.iwjw.fin.handler.test.impl;

import org.springframework.stereotype.Service;

import com.iwjw.fin.handler.service.annotation.HandlerService;
import com.iwjw.fin.handler.test.TestHandler;

/**
 * <p>
 * <P>
 * 
 * @author caojiayao
 * @version $Id: TestHandlerImpl.java, v 0.1 2017年4月19日 下午5:11:02 caojiayao Exp
 *          $
 */
@HandlerService
@Service
public class TestHandlerImpl implements TestHandler {

	@Override
	public String test(String name) {
		return "hello work ,name:" + name;
	}

	@Override
	public String test1() {
		return "hello work ";
	}

}
