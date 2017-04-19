/*
 * @author caojiayao 2017年4月19日 下午5:21:41
 */
package com.iwjw.fin.handler.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.iwjw.fin.handler.annotation.EnableHandler;

/**
 * <p>
 * <P>
 * 
 * @author caojiayao
 * @version $Id: TestApplication.java, v 0.1 2017年4月19日 下午5:21:41 caojiayao Exp
 *          $
 */
@SpringBootApplication
@EnableHandler
public class TestApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder().web(true).sources(TestApplication.class).run(args);
	}

}
